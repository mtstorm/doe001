package se.skillytaire.cases.doe.view.swing;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.common.TochtType;
import se.skillytaire.cases.doe.service.BootInformatie;
import se.skillytaire.cases.doe.service.KlantInformatie;
import se.skillytaire.cases.doe.service.StartTochtInformatie;
import se.skillytaire.cases.doe.service.TochtEinde;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

public class KarelsVerhuurbedrijfInspectie extends JFrame {
	private static final long serialVersionUID = 1L;
	private final VerhuurderService karel;
	private javax.swing.JPanel panelMain = null;
	private javax.swing.JPanel panelKnoppen = null;
	private javax.swing.JList<String> listUitvoer = null;
	private javax.swing.JButton buttonStartMeerTocht = null;
	private javax.swing.JButton buttonStartRiviertocht = null;
	private javax.swing.JButton buttonEindeTocht = null;
	private javax.swing.JTextField textBootnummerEindeTocht = null;
	private javax.swing.JButton buttonUitvoerenInspectie = null;
	private javax.swing.JTextField textBootnummerUitvoerenInspectie = null;
	private javax.swing.JButton buttonAantalBeeindigdeTochten = null;
	private javax.swing.JButton buttonGemiddeldeDuur = null;
	private javax.swing.JButton buttonBootInformatie = null;
	private javax.swing.JTextField textBootnummerInformatie = null;
	private javax.swing.JButton buttonRegistrerenKlant = null;
	private javax.swing.JButton buttonReserveren = null;
	private javax.swing.JButton buttonKlantInformatie = null;
	private JTextField textKlantnummerInformatie = null;
	private javax.swing.JButton buttonBotenOverzicht = null;
	private javax.swing.JButton buttonAfsluiten = null;
	private javax.swing.DefaultListModel<String> ivjOverzichtModel = null;
	private javax.swing.JScrollPane scrollPaneUitvoer = null;

	public static void main(final String[] args) {
		new KarelsVerhuurbedrijfInspectie().setVisible(true);
	}

	public KarelsVerhuurbedrijfInspectie() {
		super();
		initialize();
		karel = VerhuurderServiceImpl.getInstance();
	}

	private void initialize() {
		setContentPane(getPanelMain());
		// this.setSize(561, 381);
		this.setSize(561, 486);
		setTitle("Dana's Outdoor Experience");
		setResizable(false);
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

	}

	private javax.swing.JPanel getPanelMain() {
		if (panelMain == null) {
			panelMain = new javax.swing.JPanel();
			panelMain.setLayout(new javax.swing.BoxLayout(panelMain, javax.swing.BoxLayout.X_AXIS));
			panelMain.add(getPanelKnoppen(), null);
			panelMain.add(getScrollPaneUitvoer(), null);
			panelMain.setBorder(new javax.swing.border.SoftBevelBorder(BevelBorder.LOWERED));
		}
		return panelMain;
	}

	private javax.swing.JPanel getPanelKnoppen() {
		if (panelKnoppen == null) {
			panelKnoppen = new javax.swing.JPanel();
			panelKnoppen.setLayout(null);
			panelKnoppen.add(getButtonStartMeerTocht(), null);
			panelKnoppen.add(getButtonStartRiviertocht(), null);
			panelKnoppen.add(getButtonEindeTocht(), null);
			panelKnoppen.add(getTextBootnummerEindeTocht(), null);
			panelKnoppen.add(getButtonUitvoerenInspectie(), null);
			panelKnoppen.add(getTextBootnummerUitvoerenInspectie(), null);
			panelKnoppen.add(getButtonAantalBeeindigdeTochten(), null);
			panelKnoppen.add(getButtonGemiddeldeDuur(), null);
			panelKnoppen.add(getButtonBootnummerInformatie(), null);
			panelKnoppen.add(getTextBootnummerInformatie(), null);
			panelKnoppen.add(getButtonRegistrerenKlant(), null);
			panelKnoppen.add(getButtonReserveren(), null);
			panelKnoppen.add(getButtonKlantInformatie(), null);
			panelKnoppen.add(getTextKlantnummerInformatie(), null);
			panelKnoppen.add(getButtonBotenOverzicht(), null);
			panelKnoppen.add(getButtonAfsluiten(), null);
			panelKnoppen.setPreferredSize(new java.awt.Dimension(180, 100));
		}
		return panelKnoppen;
	}

	private javax.swing.JList<String> getListUitvoer() {
		if (listUitvoer == null) {
			listUitvoer = new javax.swing.JList<>();
			listUitvoer.setModel(getIvjOverzichtModel());
			listUitvoer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		}
		return listUitvoer;
	}

	private javax.swing.JButton getButtonStartMeerTocht() {
		if (buttonStartMeerTocht == null) {
			buttonStartMeerTocht = new javax.swing.JButton();
			buttonStartMeerTocht.setSize(185, 27);
			buttonStartMeerTocht.setText("Start Meertocht");
			buttonStartMeerTocht.setLocation(10, 10);
			buttonStartMeerTocht.addActionListener(e -> {
				int nr = 0;
				final String[] args = new String[0];
				final StartTochtDialog startDialog = new StartTochtDialog("Start tocht", args);
				if (startDialog.isActionOK()) {
					final String typeBoot = startDialog.getTypeBoot();
					final Duration duur = startDialog.getDuur();
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Type Boot " + typeBoot);
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Duur " + duur);

					switch (typeBoot) {
					case "Elektrisch": {
						final StartTochtInformatie info = new StartTochtInformatie(VoertuigType.ELEKTRISCHE_BOOT, TochtType.MEER,
								duur);
						nr = karel.startTocht(info);
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
								.addElement("Meertocht gestart met Elektrische boot " + nr);
						break;
					}
					case "RoeiBoot": {
						final StartTochtInformatie info = new StartTochtInformatie(VoertuigType.ROEI_BOOT, TochtType.MEER, duur);
						nr = karel.startTocht(info);
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
								.addElement("Meertocht gestart met Roeiboot " + nr);
						break;
					}
					default:
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Error boottype");
						break;
					}
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
					updateOverzichtVrijeBoten();
				}
			});
		}
		return buttonStartMeerTocht;
	}

	private javax.swing.JButton getButtonStartRiviertocht() {
		if (buttonStartRiviertocht == null) {
			buttonStartRiviertocht = new javax.swing.JButton();
			buttonStartRiviertocht.setSize(185, 27);
			buttonStartRiviertocht.setLocation(10, 45);
			buttonStartRiviertocht.setText("Start Riviertocht");
			buttonStartRiviertocht.addActionListener(e -> {
				try {
					final Duration duur = Duration.ofHours(2);
					final StartTochtInformatie info = new StartTochtInformatie(VoertuigType.ROEI_BOOT, TochtType.RIVIER, duur);
					final int nr = karel.startTocht(info);

					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Riviertocht gestart met boot " + nr);
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
					updateOverzichtVrijeBoten();
				} catch (final IllegalStateException parm) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(parm.getMessage());
				}
			});
		}
		return buttonStartRiviertocht;
	}

	private javax.swing.JButton getButtonEindeTocht() {
		if (buttonEindeTocht == null) {
			buttonEindeTocht = new javax.swing.JButton();
			buttonEindeTocht.setSize(145, 27);
			buttonEindeTocht.setText("Beeindig Tocht");
			buttonEindeTocht.setLocation(10, 80);
			buttonEindeTocht.addActionListener(e -> {
				try {
					final int nr = Integer
							.parseInt(KarelsVerhuurbedrijfInspectie.this.getTextBootnummerEindeTocht().getText());
					final TochtEinde einde = karel.beeindigTocht(nr);
					final Duration deDuur = einde.getDuur();
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Boot " + nr
							+ " is terug. Duur: " + KarelsVerhuurbedrijfInspectie.durationToString(deDuur));
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
					final String bootInspectieMsg = "Boot " + nr + " heeft " + (einde.isInspectieNodig() ? "" : "g")
							+ "een inspectie nodig";
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(bootInspectieMsg);
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
					updateOverzichtVrijeBoten();
				} catch (final NumberFormatException parm1) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Bootnummer niet correct gevuld");
				} catch (final IllegalStateException parm2) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(parm2.getMessage());
				}

			});
		}
		return buttonEindeTocht;
	}

	private javax.swing.JTextField getTextBootnummerEindeTocht() {
		if (textBootnummerEindeTocht == null) {
			textBootnummerEindeTocht = new javax.swing.JTextField();
			textBootnummerEindeTocht.setSize(35, 25);
			textBootnummerEindeTocht.setLocation(160, 81);
		}
		return textBootnummerEindeTocht;
	}

	private javax.swing.JButton getButtonUitvoerenInspectie() {
		if (buttonUitvoerenInspectie == null) {
			buttonUitvoerenInspectie = new javax.swing.JButton();
			buttonUitvoerenInspectie.setSize(145, 27);
			buttonUitvoerenInspectie.setText("Uitvoeren inspectie");
			buttonUitvoerenInspectie.setLocation(10, 115);
			buttonUitvoerenInspectie.addActionListener(e -> {
				try {
					final int nr = Integer.parseInt(
							KarelsVerhuurbedrijfInspectie.this.getTextBootnummerUitvoerenInspectie().getText());
					karel.uitvoerenInspectie(nr);
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Boot " + nr + " is geinspecteerd");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
					updateOverzichtVrijeBoten();
				} catch (final NumberFormatException parm1) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Bootnummer niet correct gevuld");
				} catch (final IllegalStateException parm2) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(parm2.getMessage());
				}

			});
		}
		return buttonUitvoerenInspectie;
	}

	private javax.swing.JTextField getTextBootnummerUitvoerenInspectie() {
		if (textBootnummerUitvoerenInspectie == null) {
			textBootnummerUitvoerenInspectie = new javax.swing.JTextField();
			textBootnummerUitvoerenInspectie.setSize(35, 25);
			textBootnummerUitvoerenInspectie.setLocation(160, 116);
		}
		return textBootnummerUitvoerenInspectie;
	}

	private javax.swing.JButton getButtonAantalBeeindigdeTochten() {
		if (buttonAantalBeeindigdeTochten == null) {
			buttonAantalBeeindigdeTochten = new javax.swing.JButton();
			buttonAantalBeeindigdeTochten.setSize(185, 27);
			buttonAantalBeeindigdeTochten.setText("Aantal Beeindigde Tochten");
			buttonAantalBeeindigdeTochten.setLocation(10, 150);
			buttonAantalBeeindigdeTochten.addActionListener(e -> {
				final int aantal = karel.geefAantalBeeindigdeTochten();
				KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
						.addElement("Aantal beeindigde tochten: " + aantal);
				listUitvoer
						.ensureIndexIsVisible(KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
			});
		}
		return buttonAantalBeeindigdeTochten;
	}

	private javax.swing.JButton getButtonGemiddeldeDuur() {
		if (buttonGemiddeldeDuur == null) {
			buttonGemiddeldeDuur = new javax.swing.JButton();
			buttonGemiddeldeDuur.setSize(185, 27);
			buttonGemiddeldeDuur.setText("Gemiddelde Duur");
			buttonGemiddeldeDuur.setLocation(10, 185);
			buttonGemiddeldeDuur.addActionListener(e -> {
				final Duration gemiddelde = karel.geefGemiddeldeDuur();
				KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
						.addElement("Gemiddelde duur: " + KarelsVerhuurbedrijfInspectie.durationToString(gemiddelde));
				listUitvoer
						.ensureIndexIsVisible(KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
			});
		}
		return buttonGemiddeldeDuur;
	}

	private javax.swing.JButton getButtonBootnummerInformatie() {
		if (buttonBootInformatie == null) {
			buttonBootInformatie = new javax.swing.JButton();
			buttonBootInformatie.setSize(145, 27);
			buttonBootInformatie.setText("Opvragen bootinfo");
			buttonBootInformatie.setLocation(10, 220);
			buttonBootInformatie.addActionListener(e -> {
				try {
					final int bootnr = Integer
							.parseInt(KarelsVerhuurbedrijfInspectie.this.getTextBootnummerInformatie().getText());
					final String title = "Bootinformatie " + bootnr;
					final BootInformatie bootTO = karel.getBootInformatie(bootnr);
					final String[] columnNames = { "Boot type", "Aantal personen", "Verwachte eindtijd" };

					final Object[][] columnData = {
							{ bootTO.getType(), bootTO.getAantalPersonen(), bootTO.getVerwachteEindtijd() },
							// {"John", "Doe","Rowing", new Integer(3), new Boolean(true)},
							// {"Sue", "Black","Knitting", new Integer(2), new Boolean(false)},
							// {"Jane", "White","Speed reading", new Integer(20), new Boolean(true)},
							// {"Joe", "Brown","Pool", new Integer(10), new Boolean(false)}
					};

					JScrollPaneDemo.createAndShowGUI(title, columnNames, columnData);

					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Boot " + bootnr + " Informatie opgehaald");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
				} catch (final NumberFormatException parm1) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Bootnummer niet correct gevuld");
				} catch (final IllegalStateException parm2) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(parm2.getMessage());
				}
			});
		}
		return buttonBootInformatie;
	}

	private javax.swing.JTextField getTextBootnummerInformatie() {
		if (textBootnummerInformatie == null) {
			textBootnummerInformatie = new javax.swing.JTextField();
			textBootnummerInformatie.setSize(35, 25);
			textBootnummerInformatie.setLocation(160, 220);
		}
		return textBootnummerInformatie;
	}

	private javax.swing.JButton getButtonRegistrerenKlant() {
		if (buttonRegistrerenKlant == null) {
			buttonRegistrerenKlant = new javax.swing.JButton();
			buttonRegistrerenKlant.setSize(185, 27);
			buttonRegistrerenKlant.setText("Registreren klant");
			buttonRegistrerenKlant.setLocation(10, 255);
			buttonRegistrerenKlant.addActionListener(e -> {
				final String[] args = new String[0];
				final JTextFieldDemo klant = new JTextFieldDemo("Registreren klant", args);
				if (klant.isActionOK()) {
					int klantNummer;
					final String naam = klant.getName();
					final String adres = klant.getAddress();
					final String woonplaats = klant.getCity();
					final String telefoonnummer = klant.getPhone();
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Klant naam " + naam);
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Klant adres " + adres);
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Klant woonplaats " + woonplaats);
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Klant telefoonnummer " + telefoonnummer);
					KlantInformatie info= new KlantInformatie(naam, adres, woonplaats, telefoonnummer);
					klantNummer = karel.registrerenKlant(info);
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Klant " + klantNummer + " geregistreerd");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);

				} else {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Geen klant geregistreerd");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);

				}
			});
		}
		return buttonRegistrerenKlant;
	}

	private javax.swing.JButton getButtonReserveren() {
		if (buttonReserveren == null) {
			buttonReserveren = new javax.swing.JButton();
			buttonReserveren.setSize(185, 27);
			buttonReserveren.setText("Reserveren");
			buttonReserveren.setLocation(10, 290);
			buttonReserveren.addActionListener(e -> {
				final String[] args = new String[0];
				final ReserveringDialog reservering = new ReserveringDialog("Reserveren", args);
				if (reservering.isActionOK()) {
					final String typeTocht = reservering.getTypeTocht();
					final String typeBoot = reservering.getTypeBoot();
					final LocalDate datum = reservering.getDatum();
					final LocalTime tijd = reservering.getTijd();
					final Duration duur = reservering.getDuur();

					final LocalDateTime datumtijd = LocalDateTime.of(datum, tijd);

					final Boolean reserverenGeslaagd = karel.reserveren(typeTocht, typeBoot, datumtijd, duur);
					if (reserverenGeslaagd) {
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Reservering vastgelegd");
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Type Tocht " + typeTocht);
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Type Boot " + typeBoot);
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Datum " + datum);
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Tijd " + tijd);
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Duur " + duur);
						updateOverzichtVrijeBoten();
					} else {
						KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
								.addElement("Reservering niet mogelijk");
					}
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("-------------------------------");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);

				} else {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Geen klant geregistreerd");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);

				}
			});
		}
		return buttonReserveren;
	}

	private javax.swing.JButton getButtonKlantInformatie() {
		if (buttonKlantInformatie == null) {
			buttonKlantInformatie = new javax.swing.JButton();
			buttonKlantInformatie.setSize(145, 27);
			buttonKlantInformatie.setText("Opvragen klantinfo");
			buttonKlantInformatie.setLocation(10, 325);
			buttonKlantInformatie.addActionListener(e -> {
				try {
					final int klantNummer = Integer
							.parseInt(KarelsVerhuurbedrijfInspectie.this.getTextKlantnummerInformatie().getText());
					final String title = "Klantinformatie " + klantNummer;
					final KlantInformatie klantTO = karel.getKlantInformatie(klantNummer);
					final String[] columnNames = { "Naam", "Adres", "Woonplaats", "Telefoonnr" };

					final Object[][] columnData = { { klantTO.geefNaam(), klantTO.geefAdres(), klantTO.geefWoonplaats(),
							klantTO.geefTelefoonnr() },
							// {"John", "Doe","Rowing", new Integer(3), new Boolean(true)},
							// {"Sue", "Black","Knitting", new Integer(2), new Boolean(false)},
							// {"Jane", "White","Speed reading", new Integer(20), new Boolean(true)},
							// {"Joe", "Brown","Pool", new Integer(10), new Boolean(false)}
					};

					JScrollPaneDemo.createAndShowGUI(title, columnNames, columnData);

					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Klant" + klantNummer + " Informatie opgehaald");
					listUitvoer.ensureIndexIsVisible(
							KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
				} catch (final NumberFormatException parm1) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel()
							.addElement("Klantnummer niet correct gevuld");
				} catch (final IllegalStateException parm2) {
					KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(parm2.getMessage());
				}
			});
		}
		return buttonKlantInformatie;
	}

	private javax.swing.JTextField getTextKlantnummerInformatie() {
		if (textKlantnummerInformatie == null) {
			textKlantnummerInformatie = new javax.swing.JTextField();
			textKlantnummerInformatie.setSize(35, 25);
			textKlantnummerInformatie.setLocation(160, 325);
		}
		return textKlantnummerInformatie;
	}

	private javax.swing.JButton getButtonBotenOverzicht() {
		if (buttonBotenOverzicht == null) {
			buttonBotenOverzicht = new javax.swing.JButton();
			buttonBotenOverzicht.setSize(185, 27);
			buttonBotenOverzicht.setText("Overzicht vrije boten");
			buttonBotenOverzicht.setLocation(10, 360);
			buttonBotenOverzicht.addActionListener(e -> {
				try {
					toonOverzichtVrijeBoten();

					/*
					 * KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Boot "
					 * + bootnr + " Informatie opgehaald");
					 * KarelsVerhuurbedrijfInspectie.this.listUitvoer.ensureIndexIsVisible(
					 * KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().getSize() - 1);
					 */
				} catch (final NumberFormatException parm1) {
					// KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement("Bootnummer
					// niet correct gevuld");
				} catch (final IllegalStateException parm2) {
					// KarelsVerhuurbedrijfInspectie.this.getIvjOverzichtModel().addElement(parm.getMessage());
				}
			});
		}
		return buttonBotenOverzicht;
	}

	private void toonOverzichtVrijeBoten() {
		final String[] columnNames = { "Bootnummer", "Naam", "Boot type", "Aantal personen", "Inspectie nodig" };

		final ArrayList<BootInformatie> vrijeboten = KarelsVerhuurbedrijfInspectie.this.karel
				.geefVrijeBoten(LocalDateTime.now(), Duration.ofHours(2));

		Object[][] columnData = null;
		final int size = vrijeboten.size();
		if (size > 0) {
			columnData = new Object[size][5];
		}
		for (int i = 0; i < size; i++) {
			final BootInformatie bo = vrijeboten.get(i);
			columnData[i][0] = bo.getNummer();
			columnData[i][1] = bo.getNaam();
			columnData[i][2] = bo.getType();
			columnData[i][3] = bo.getAantalPersonen();
			columnData[i][4] = bo.getInspectie();
		}
		JScrollPaneDemo.createAndShowGUI("Overzicht vrije boten", columnNames, columnData);
	}

	private void updateOverzichtVrijeBoten() {
		if (JScrollPaneDemo.getFrame() != null) {
			toonOverzichtVrijeBoten();
		}

	}

	private javax.swing.JButton getButtonAfsluiten() {
		if (buttonAfsluiten == null) {
			buttonAfsluiten = new javax.swing.JButton();
			buttonAfsluiten.setSize(100, 27);
			buttonAfsluiten.setLocation(10, 395);
			buttonAfsluiten.setText("Afsluiten");
			buttonAfsluiten.addActionListener(e -> {
				KarelsVerhuurbedrijfInspectie.this.dispose();
				if (JScrollPaneDemo.getFrame() != null) {
					JScrollPaneDemo.getFrame().dispose();
				}
			});
		}
		return buttonAfsluiten;
	}

	private javax.swing.DefaultListModel<String> getIvjOverzichtModel() {
		if (ivjOverzichtModel == null) {
			ivjOverzichtModel = new javax.swing.DefaultListModel<>();
		}
		return ivjOverzichtModel;
	}

	private javax.swing.JScrollPane getScrollPaneUitvoer() {
		if (scrollPaneUitvoer == null) {
			scrollPaneUitvoer = new javax.swing.JScrollPane();
			scrollPaneUitvoer.setViewportView(getListUitvoer());
			scrollPaneUitvoer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneUitvoer.setBorder(new javax.swing.border.SoftBevelBorder(BevelBorder.LOWERED));
		}
		return scrollPaneUitvoer;
	}

	private static String durationToString(final Duration duration) {
		final long seconds = duration == null ? 0 : duration.getSeconds();
		return String.format("%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
	}

}
