package se.skillytaire.cases.doe.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import se.skillytaire.cases.doe.common.TochtType;
import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.domain.Boot;
import se.skillytaire.cases.doe.domain.BootBestaatNietException;
import se.skillytaire.cases.doe.domain.ElektrischeBoot;
import se.skillytaire.cases.doe.domain.GeenBootVrijException;
import se.skillytaire.cases.doe.domain.Klant;
import se.skillytaire.cases.doe.domain.Klantenbak;
import se.skillytaire.cases.doe.domain.RoeiBoot;
import se.skillytaire.cases.doe.domain.Tocht;
import se.skillytaire.cases.doe.domain.Tochtenbak;
import se.skillytaire.cases.doe.domain.Vloot;
import se.skillytaire.cases.doe.service.BootInformatie;
import se.skillytaire.cases.doe.service.KlantInformatie;
import se.skillytaire.cases.doe.service.StartTochtInformatie;
import se.skillytaire.cases.doe.service.TochtEinde;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.toa.BootTransferableObjectAssembler;
import se.skillytaire.cases.doe.service.toa.KlantTransferableObjectAssembler;

public class VerhuurderServiceImpl implements VerhuurderService, Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SERFILE = "verhuurder.ser";
	private static final String USER_HOME = System.getProperty("user.home");
	private static final Path SER_FILE_PATH = Paths.get(VerhuurderServiceImpl.USER_HOME, VerhuurderServiceImpl.SERFILE);
	private static final Logger log = Logger.getLogger(VerhuurderServiceImpl.class.getName());
	private static final VerhuurderServiceImpl instance;

	static {

		if (Files.exists(VerhuurderServiceImpl.SER_FILE_PATH)) {
			VerhuurderServiceImpl.log.info("Loading the state of the verhuurder");
			try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(
					Files.newInputStream(VerhuurderServiceImpl.SER_FILE_PATH, StandardOpenOption.READ)))) {
				instance = (VerhuurderServiceImpl) ois.readObject();
				VerhuurderServiceImpl.log.info("The state of the verhuurder is loaded");
			} catch (final IOException e) {
				throw new IllegalStateException("Error reading the state of the verhuurder from path "
						+ VerhuurderServiceImpl.SER_FILE_PATH.toAbsolutePath(), e);
			} catch (final ClassNotFoundException e) {
				throw new IllegalStateException("Error reading the state of the verhuurder, classpath problem", e);
			}
		} else {
			VerhuurderServiceImpl.log.info("Loading initial state for the verhuurder");
			instance = new VerhuurderServiceImpl();
		}
	}

	private final Tochtenbak eenTochtenbak;
	private final Vloot eenVloot;
	private final Klantenbak eenKlantenbak;

	private VerhuurderServiceImpl() {
		eenTochtenbak = new Tochtenbak();
		eenVloot = new Vloot();
		eenKlantenbak = new Klantenbak();

		final String[] namenRoeiboten = { "Patrick de Verschrikkelijke", "blaadje", "bloemetje", "veertje", "clara" };
		final String[] namenElektrischeboten = { "De Grote Hugo", "Petra de Verkenner", "vliegende hollander",
				"vallende fransman", "spaanse furie" };

		for (int i = 0; i < namenRoeiboten.length; i++) {
			final RoeiBoot eenBoot = new RoeiBoot(1 + i, namenRoeiboten[i], 4, 25);
			eenVloot.add(eenBoot);
			final ElektrischeBoot eenBoot2 = new ElektrischeBoot(100 + i, namenElektrischeboten[i], 6, 25);
			eenVloot.add(eenBoot2);
		}
	}

	public static VerhuurderServiceImpl getInstance() {
		return VerhuurderServiceImpl.instance;
	}

	public static VerhuurderServiceImpl getInstance(boolean clear) {
		if (clear) {
			VerhuurderServiceImpl.instance.eenVloot.clear();
			VerhuurderServiceImpl.instance.eenTochtenbak.clear();
			VerhuurderServiceImpl.instance.persist();
		}
		return VerhuurderServiceImpl.instance;
	}

	@Override
	public TochtEinde beeindigTocht(final int bootNummer) throws BootBestaatNietException {
		Duration duur;
		final Boot eenBoot = eenVloot.getBoot(bootNummer);
		duur = eenBoot.beeindigTocht();
		final boolean inspectie = eenBoot.isInspectieNodig();
		final TochtEinde einde = new TochtEinde(duur, inspectie);
		persist();
		return einde;
	}

	@Override
	public int geefAantalBeeindigdeTochten() {
		int aantal;
		aantal = eenTochtenbak.geefAantalBeeindigdeTochten();
		return aantal;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see se.skillytaire.cases.doe.service.VerhuurderService#geefGemiddeldeDuur()
	 */
	@Override
	public Duration geefGemiddeldeDuur() {
		Duration duur;
		duur = eenTochtenbak.geefGemiddeldeDuur();
		return duur;
	}

	@Override
	public int startTocht(final StartTochtInformatie info) {
		int bootnummer;
		Boot eenBoot;
		final TochtType tochtType = info.getTochtType();
		final Duration verwachteDuur = info.getVerwachteDuur();
		if (info.isByNumber()) {
			bootnummer = info.getBootNummer();
			final LocalDateTime beginTijd = LocalDateTime.now();
			Predicate<Boot> p = (b) -> {
				return b.getNummer() == bootnummer && b.isBeschikbaar() && b.isVrij(beginTijd, verwachteDuur);
			};
			eenBoot = eenVloot.find(p);
		} else {
			final LocalDateTime beginTijd = LocalDateTime.now();
			final VoertuigType bootType = info.getBootType();
			final Predicate<Boot> criteria = (boot) -> {
				return boot.getType().equals(bootType) && boot.isVrij(beginTijd, verwachteDuur) && boot.isBeschikbaar();
			};
			eenBoot = eenVloot.geefVrijeBoot(criteria);
			bootnummer = eenBoot.getNummer();
		}

		final Tocht nieuweTocht = Tocht.create(eenBoot.getType(), tochtType, verwachteDuur);
		eenBoot.startTocht(nieuweTocht);
		eenTochtenbak.bewaar(nieuweTocht);
		persist();
		return bootnummer;
	}

	@Override
	public void uitvoerenInspectie(final int nummer) throws BootBestaatNietException {
		final Boot eenBoot = eenVloot.getBoot(nummer);
		eenBoot.uitvoerenInspectie();
		persist();
	}

	@Override
	public boolean eindeDag() {
		return eenTochtenbak.zijnAlleTochtenBeindigd();
	}

	@Override
	public BootInformatie getBootInformatie(final int bootnr) {
		final Boot eenBoot = eenVloot.getBoot(bootnr);
		final BootInformatie to = BootTransferableObjectAssembler.getInstance().assemble(eenBoot);
		return to;
	}

	@Override
	public KlantInformatie getKlantInformatie(final int klantNummer) {
		final Klant eenKlant = eenKlantenbak.geefKlant(klantNummer);		
		return KlantTransferableObjectAssembler.getInstance().assemble(eenKlant);
	}

	private void persist() {

		VerhuurderServiceImpl.log.info("Saving the state of the verhuurder");
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(Files.newOutputStream(VerhuurderServiceImpl.SER_FILE_PATH,
						StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)))) {
			oos.writeObject(this);
			VerhuurderServiceImpl.log.info("The state of the verhuurder is saved");
		} catch (final IOException e) {
			throw new IllegalStateException("Error writing the state of the verhuurder from path "
					+ VerhuurderServiceImpl.SER_FILE_PATH.toAbsolutePath(), e);
		}

	}

	@Override
	public int registrerenKlant(KlantInformatie klantInfo) {
		Klant klant=KlantTransferableObjectAssembler.getInstance().assemble(klantInfo);
		eenKlantenbak.bewaar(klant);
		persist();
		return klant.getKlantNummer();
	}

	@Override
	public ArrayList<BootInformatie> geefVrijeBoten(final LocalDateTime datumtijd, final Duration duur) {
		final List<Boot> vrijeboten = eenVloot.findBoten(p -> p.isVrij(datumtijd, duur));
		final ArrayList<BootInformatie> vrijebotentransfer = new ArrayList<>();
		for (final Boot eenBoot : vrijeboten) {
			final BootInformatie transferobject = BootTransferableObjectAssembler.getInstance().assemble(eenBoot);
			vrijebotentransfer.add(transferobject);
		}

		return vrijebotentransfer;
	}

	@Override
	public boolean reserveren(final String typeTocht, final String typeBoot, final LocalDateTime datumtijd,
			final Duration duur) throws GeenBootVrijException {
		Boot eenBoot = null;
		if (typeBoot.equals("RoeiBoot")) {
			eenBoot = eenVloot.geefVrijeRoeiBoot(datumtijd, duur);
			if (eenBoot != null && typeTocht.equals("MeerTocht")) {
				final Tocht eenTocht = eenBoot.reserveerMeerTocht(datumtijd, duur);
				return eenTocht != null;
			} else if (eenBoot != null && typeTocht.equals("RivierTocht")) {
				final Tocht eenTocht = eenBoot.reserveerRivierTocht(datumtijd, duur);
				return eenTocht != null;
			}
		} else if (typeBoot.equals("Elektrisch")) {
			eenBoot = eenVloot.geefVrijeElektrischeBoot(datumtijd, duur);
			if (eenBoot != null && typeTocht.equals("MeerTocht")) {
				final Tocht eenTocht = eenBoot.reserveerMeerTocht(datumtijd, duur);
				return eenTocht != null;
			} else if (eenBoot != null && typeTocht.equals("RivierTocht")) {
				final Tocht eenTocht = eenBoot.reserveerRivierTocht(datumtijd, duur);
				return eenTocht != null;
			}
		}
		return eenBoot != null;
	}

	

	@Override
	public boolean createBoot(BootInformatie bootinfo) {
		BootTransferableObjectAssembler btoa = BootTransferableObjectAssembler.getInstance();
		Boot newBoot = btoa.assemble(bootinfo);
		return eenVloot.add(newBoot);
	}

	@Override
	public int getAantalBoten() {

		return this.eenVloot.size();
	}

}
