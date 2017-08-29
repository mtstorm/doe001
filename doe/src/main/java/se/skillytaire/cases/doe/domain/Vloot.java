package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import javax.persistence.Entity;

@Entity
public class Vloot extends Bak<Boot> {
	private static  long serialVersionUID = 1L;

	public Boot getBoot( int nummer) throws BootBestaatNietException {
		Boot eenBoot = null;
		for (int i = 0; i < size(); i++) {
			 Boot boot = get(i);
			if (boot.hasNummer(nummer)) {
				eenBoot = boot;
				break;
			}
		}
		if (eenBoot == null) {
			throw new BootBestaatNietException("Boot " + nummer + " bestaat niet in vloot");
		}
		return eenBoot;
	}

	/**
	 * @deprecated liever get met criteria
	 * @param criteria
	 * @return
	 */
	@Deprecated
	public Boot geefVrijeBoot( Predicate<Boot> criteria) {
		Boot eenBoot = null;
		for (int i = 0; i < size(); i++) {
			 Boot lusBoot = get(i);
			if (criteria.test(lusBoot)) {
				eenBoot = lusBoot;
				break;
			}
		}
		if (eenBoot == null) {
			throw new GeenBootVrijException("Geen boot beschikbaar in de vloot");
		}
		return eenBoot;
	}

	/**
	 * @deprecated liever get met criteria
	 * @param criteria
	 * @return
	 */
	@Deprecated
	public Boot geefVrijeRoeiBoot( LocalDateTime beginTijd,  Duration duur) throws GeenBootVrijException {
		Boot eenBoot = null;
		for (int i = 0; i < size(); i++) {
			 Boot lusBoot = get(i);
			if (lusBoot.getClass().getSimpleName().equals("RoeiBoot") && lusBoot.isVrij(beginTijd, duur)) {
				eenBoot = lusBoot;
				break;
			}
		}
		if (eenBoot == null) {
			throw new GeenBootVrijException("Geen boot beschikbaar in de vloot");
		}
		return eenBoot;
	}

	/**
	 * @deprecated liever get met criteria
	 * @param criteria
	 * @return
	 */
	@Deprecated
	public Boot geefVrijeElektrischeBoot( LocalDateTime beginTijd,  Duration duur)
			throws GeenBootVrijException {
		Boot eenBoot = null;
		for (int i = 0; i < size(); i++) {
			 Boot lusBoot = get(i);
			if (lusBoot.getClass().getSimpleName().equals("ElektrischeBoot") && lusBoot.isVrij(beginTijd, duur)) {
				eenBoot = lusBoot;
				break;
			}
		}
		if (eenBoot == null) {
			throw new GeenBootVrijException("Geen boot beschikbaar in de vloot");
		}
		return eenBoot;
	}

	public List<Boot> findBoten( Predicate<Boot> p) {
		 List<Boot> boten = new ArrayList<>();
		for (int index = 0; index < size(); index++) {
			Boot eenBoot = this.get(index);
			if (p.test(eenBoot)) {
				boten.add(eenBoot);
			}
		}
		return boten;
	}

	public Boot find( Predicate<Boot> p) {
		Boot boot;
		Collection<Boot> result = findBoten(p);
		if(result.isEmpty()) {
			boot = null;
		} else {
			boot = result.iterator().next();
		}
		return boot;
	}
}
