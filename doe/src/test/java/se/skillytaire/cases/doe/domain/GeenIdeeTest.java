package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class GeenIdeeTest {

	@Test
	public void test() {
		final String[] bootNamen = { "Patrick de Verschrikkelijke", "blaadje", "bloemetje", "veertje", "clara" };
		final String[] bootNamen2 = { "De Grote Hugo", "Petra de Verkenner", "vliegende hollander", "vallende fransman",
				"spaanse furie" };

		final Vloot groteVloot = new Vloot();

		for (int i = 0; i < bootNamen.length; i++) {
			final Boot eenBoot = new RoeiBoot(i * i + 1, bootNamen[i], 4, 15);
			final Boot eenBoot2 = new ElektrischeBoot(i * i + 100, bootNamen2[i], 6, 25);
			groteVloot.add(eenBoot);
			groteVloot.add(eenBoot2);
		}

		for (int i = 0; i < bootNamen.length; i++) {
			System.out.printf("Type = %-20s, Naam = %-30s, Aantal Personen =  %-2d\n",
					groteVloot.getBoot(i * i + 1).getClass().getSimpleName(), groteVloot.getBoot(i * i + 1).getNaam(),
					groteVloot.getBoot(i * i + 1).getAantalPersonen());
			System.out.printf("Type = %-20s, Naam = %-30s, Aantal Personen =  %-2d\n",
					groteVloot.getBoot(i * i + 100).getClass().getSimpleName(),
					groteVloot.getBoot(i * i + 100).getNaam(), groteVloot.getBoot(i * i + 100).getAantalPersonen());
			Assert.assertTrue(groteVloot.getBoot(i * i + 1).getNaam().equals(bootNamen[i])
					&& groteVloot.getBoot(i * i + 1).getAantalPersonen() == 4);
			Assert.assertTrue(groteVloot.getBoot(i * i + 100).getNaam().equals(bootNamen2[i])
					&& groteVloot.getBoot(i * i + 100).getAantalPersonen() == 6);

		}
	}

	@Test
	public void test2() {
		// fail("Not yet implemented");
		final Duration[] dur = { Duration.ofSeconds(4 * 60), Duration.ofSeconds(2 * 60), Duration.ofSeconds(3 * 60),
				Duration.ofHours(8), Duration.ofHours(47) };

		System.out.println("Meertochten, begintijd en verwachte eindtijd");
		for (final Duration element : dur) {
			final Tocht eenMeerTocht = new MeerTocht(element);
			System.out.println(eenMeerTocht.getBeginTijd() + ",   " + eenMeerTocht.getVerwachteEindTijd());
			Assert.assertTrue(eenMeerTocht.getVerwachteEindTijd().equals(eenMeerTocht.getBeginTijd().plus(element)));
		}

		System.out.println("Riviertochten met dezelfde begintijd en verwachte eindtijd");
		for (final Duration element : dur) {
			final Tocht eenRivierTocht = new RivierTocht(element);
			System.out.println(eenRivierTocht.getBeginTijd() + ",   " + eenRivierTocht.getVerwachteEindTijd());
			Assert.assertTrue(eenRivierTocht.getVerwachteEindTijd()
					.equals(eenRivierTocht.getBeginTijd().plus(element).plusMinutes(10)));
		}

	}

	/*
	 * @Test public void test3() { String[] bootNamen =
	 * {"Patrick de Verschrikkelijke","blaadje", "bloemetje", "veertje", "clara"};
	 * String[] bootNamen2= {"De Grote Hugo", "Petra de Verkenner",
	 * "vliegende hollander", "vallende fransman", "spaanse furie"};
	 *
	 * Vloot groteVloot = new Vloot();
	 *
	 *
	 * for (int i = 0; i < bootNamen.length; i++) { Boot eenBoot = new RoeiBoot(i *
	 * i+1,bootNamen[i],4,15); Boot eenBoot2 = new ElektrischeBoot(i *i+100,
	 * bootNamen2[i], 6,25); groteVloot.voegToe(eenBoot);
	 * groteVloot.voegToe(eenBoot2);
	 * eenBoot2.gaMeerTochtMaken(Duration.ofSeconds(15));
	 * eenBoot.gaRivierTochtMaken(Duration.ofSeconds(15)); try { Thread.sleep(2000);
	 * } catch(Exception e) {
	 *
	 * } }
	 *
	 * LocalDateTime huidigetijd=LocalDateTime.now();
	 * System.out.printf("%-30s %-30s %-30s\n","","De huidige tijd is: ",
	 * LocalDateTime.now()); System.out.
	 * println("-------RivierTochten met RoeiBoten-----------------------------------------------------------------------"
	 * ); for (int i = 0; i < bootNamen.length; i++) { Boot eenBoot =
	 * groteVloot.getBoot(i * i+1);
	 *
	 *
	 * if (eenBoot.isVrij(huidigetijd,Duration.ofSeconds(10))) {
	 * System.out.printf("%-30s %-30s %-30s\n", eenBoot.getNaam(),
	 * "De verwachte eindtijd is: ",
	 * eenBoot.getLaatsteTocht().getVerwachteEindtijd()); }
	 *
	 *
	 *
	 * }
	 *
	 * System.out.
	 * println("--------MeerTochten met E-boten----------------------------------------------------------------------"
	 * ); for (int i = 0; i < bootNamen.length; i++) { Boot eenBoot =
	 * groteVloot.getBoot(i * i+100);
	 *
	 * if (eenBoot.isVrij(huidigetijd,Duration.ofSeconds(10).plusMinutes(10))) {
	 * System.out.printf("%-30s %-30s %-30s\n", eenBoot.getNaam(),
	 * "De verwachte eindtijd is: ",
	 * eenBoot.getLaatsteTocht().getVerwachteEindtijd());
	 *
	 * } } }
	 */

	/*
	 * @Test public void test4() { RoeiBoot boot = new RoeiBoot(4,"pietje",4,15);
	 *
	 * Tocht eenTocht = boot.gaMeerTochtMaken(Duration.ofMinutes(1));
	 * System.out.println("tocht 1 gestart"); boot.print(); Duration duur =
	 * boot.beeindigTocht(); System.out.println("tocht 1 beeindigd"); boot.print();
	 *
	 * Tocht tweedeTocht = boot.gaMeerTochtMaken(Duration.ofSeconds(20));
	 * System.out.println("tocht 2 gestart"); boot.print(); Duration tweededuur =
	 * boot.beeindigTocht(); System.out.println("tocht 2 beeindigd"); boot.print();
	 *
	 * }
	 */

	@Test
	public void test5() {
		final Boot boot = new RoeiBoot(1, "Hugo de Grote", 0, 0);
		final Boot boot2 = new ElektrischeBoot(2, "Vliegende Hollander", 0, 0);

		final LocalDateTime huidigetijd = LocalDateTime.now();
		Assert.assertTrue(boot.reserveerMeerTocht(huidigetijd.plus(Duration.ofHours(2)), Duration.ofHours(2)) != null);
		Assert.assertTrue(boot.reserveerMeerTocht(huidigetijd.plus(Duration.ofHours(5)), Duration.ofHours(2)) != null);
		Assert.assertTrue(boot.reserveerMeerTocht(huidigetijd.plus(Duration.ofHours(8)), Duration.ofHours(2)) != null);

		Assert.assertFalse(boot.reserveerMeerTocht(huidigetijd.plus(Duration.ofHours(4)), Duration.ofHours(2)) != null);
		Assert.assertTrue(boot.reserveerMeerTocht(huidigetijd, Duration.ofHours(2)) != null);
		Assert.assertFalse(boot.reserveerMeerTocht(huidigetijd, Duration.ofMinutes(121)) != null);

		Assert.assertFalse(boot2.reserveerRivierTocht(huidigetijd, Duration.ofHours(2)) != null);
		Assert.assertTrue(boot2.reserveerMeerTocht(huidigetijd, Duration.ofHours(2)) != null);

	}

	@Test
	public void test6() {
		final Boot boot = new RoeiBoot(1, "Hugo de Grote", 0, 0);
		final Boot boot2 = new ElektrischeBoot(2, "Vliegende Hollander", 0, 0);

		final LocalDateTime huidigetijd = LocalDateTime.now();
		Assert.assertTrue(boot.reserveerMeerTocht(huidigetijd.plus(Duration.ofHours(4)), Duration.ofHours(2)) != null);

		Assert.assertTrue(boot.gaMeerTochtMaken(Duration.ofHours(2)) != null);
		Assert.assertFalse(boot2.gaRivierTochtMaken(Duration.ofHours(2)) != null);

		Assert.assertFalse(boot.isVrij(huidigetijd, Duration.ofHours(3)));
		Assert.assertTrue(boot.isVrij(huidigetijd.plus(Duration.ofHours(3)), Duration.ofHours(1)));
		Assert.assertFalse(boot.isVrij(huidigetijd.plus(Duration.ofHours(3)), Duration.ofHours(2)));

		boot.beeindigTocht();
		Assert.assertTrue(boot.isVrij(huidigetijd, Duration.ofHours(4)));
		Assert.assertFalse(boot.isVrij(huidigetijd, Duration.ofHours(5)));

	}

	@Test
	public void test7() {
		final String[] bootNamen = { "Patrick de Verschrikkelijke", "blaadje", "bloemetje", "veertje", "clara" };
		final String[] bootNamen2 = { "De Grote Hugo", "Petra de Verkenner", "vliegende hollander", "vallende fransman",
				"spaanse furie" };

		final Vloot groteVloot = new Vloot();

		for (int i = 0; i < bootNamen.length; i++) {
			final Boot eenBoot = new RoeiBoot(i * i + 1, bootNamen[i], 4, 15);
			final Boot eenBoot2 = new ElektrischeBoot(i * i + 100, bootNamen2[i], 6, 25);
			groteVloot.add(eenBoot);
			groteVloot.add(eenBoot2);
		}
		printVrijeBoten(groteVloot);
	}

	private void printVrijeBoten(final Vloot vloot) {
		final LocalDateTime huidigetijd = LocalDateTime.now();
		final Duration duur = Duration.ofHours(2);
		List<Boot> vrijeboten = vloot.findBoten(p -> p.isVrij(huidigetijd, duur));
		int index = 0;

		System.out.println("-------------------------------------------------------------------------");
		for (final Boot boot : vrijeboten) {
			System.out.printf("%-5d %-30s %-20s %-3d %-4f\n", boot.getNummer(), boot.getNaam(),
					boot.getClass().getSimpleName(), boot.getAantalPersonen(), boot.getPrijs());
			if (index == 0) {
				boot.gaMeerTochtMaken(Duration.ofHours(2));
			}
			if (index == 1) {
				boot.gaRivierTochtMaken(Duration.ofHours(2));
			}
			if (index == 2) {
				boot.gaRivierTochtMaken(Duration.ofHours(2));
			}
			if (index == 3) {
				boot.gaMeerTochtMaken(Duration.ofHours(2));
			}
			index++;
		}

		System.out.println("-------------------------------------------------------------------------");

		vrijeboten = vloot.findBoten(p -> p.isVrij(huidigetijd, duur));
		index = 0;
		for (final Boot boot : vrijeboten) {
			System.out.printf("%-5s %-30s %-20s %-3s %-4s\n", boot.getNummer(), boot.getNaam(),
					boot.getClass().getSimpleName(), boot.getAantalPersonen(), boot.getPrijs());
			index++;
		}

	}

}
