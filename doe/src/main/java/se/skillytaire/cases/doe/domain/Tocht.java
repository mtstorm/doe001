package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ServiceLoader;

import se.skillytaire.cases.doe.common.TochtType;
import se.skillytaire.cases.doe.common.VoertuigType;

public abstract class Tocht extends AbstractEntity {

	private static  long serialVersionUID = 1L;

	private  Duration verwachteDuur;

	private LocalDateTime verwachteBeginTijd;

	private LocalDateTime beginTijd;

	private LocalDateTime eindTijd;
	/**
	 * Afleidbaar gegeven.
	 */
	private transient Duration duur = null;

	/**
	 * Dit maakt een gereserveerde tocht.
	 * 
	 * @param verwachteDuur
	 */
	public Tocht( LocalDateTime verwachteBeginTijd,  Duration verwachteDuur) {
		this.verwachteDuur = verwachteDuur;
		this.verwachteBeginTijd = verwachteBeginTijd;
	}
	/**
	 * deze constructor mag niet gebruikt worden
	 */
	public Tocht() {
		
	}

	/**
	 * Dit maakt een tocht die direct wordt gestart.
	 * 
	 * @param verwachteDuur
	 */
	public Tocht( Duration verwachteDuur) {
		this.verwachteDuur = verwachteDuur;
		start();
	}

	private boolean isGereserveerd() {
		return verwachteBeginTijd != null && !isBeeindigd();
	}

	/**
	 * Dit start de tocht. Als een tocht is gereserveerd kan de tocht gestart
	 * worden. Als de tocht als gestart is kan deze niet opnieuw worden gestart.
	 */
	public void start() {
		if (hasBeginTijd()) {
			throw new IllegalStateException("De tocht is al gestart en kan niet opnieuw worden gestart.");
		}
		beginTijd = LocalDateTime.now();
	}

	private boolean isGestart() {
		return beginTijd != null;
	}

	private boolean hasBeginTijd() {
		return beginTijd != null;
	}

	public boolean isBeeindigd() {
		boolean beeindigd = false;
		if (eindTijd != null) {
			beeindigd = true;
		}
		return beeindigd;
	}

	public LocalDateTime getVerwachteBeginTijd() {
		return verwachteBeginTijd;
	}

	public void beeindig() {
		if (!isGestart()) {
			throw new IllegalStateException("De tocht is nog niet gestart en kan daarom niet worden beindigd");
		}
		if (isBeeindigd()) {
			throw new IllegalStateException("De tocht is beindigd en kan daarom niet opnieuw worden beindigd");
		}
		eindTijd = LocalDateTime.now();
	}

	public Duration getDuur() {
		if (duur == null) {
			duur = Duration.between(getBeginTijd(), getEindTijd());
		}
		return duur;
	}

	public Duration getVerwachteDuur() {
		return verwachteDuur;
	}

	/*
	 * reservering -> Tocht met een verwachte begintijd gestarte tocht
	 */
	/**
	 *
	 * @return
	 */
	public LocalDateTime getVerwachteEindTijd() {
		LocalDateTime verwachteEindtijd;
		if (isGereserveerd()) {
			verwachteEindtijd = verwachteBeginTijd.plus(verwachteDuur);
		} else {
			verwachteEindtijd = getBeginTijd().plus(verwachteDuur);
		}
		return verwachteEindtijd;
	}

	private LocalDateTime getEindTijd() {
		if (!isBeeindigd()) {
			throw new IllegalArgumentException("De tocht is nog niet beindigd");
		}
		return eindTijd;
	}

	LocalDateTime getBeginTijd() {
		if (!isGestart()) {
			throw new IllegalArgumentException("De tocht is nog niet gestart");
		}
		return beginTijd;
	}

	@Override
	public String toString() {
		return super.toString() +  String.format("Tocht [isGestart=%b, isGereserveerd=%b. isBeeindigd=%b]", isGestart(), isGereserveerd(),
				isBeeindigd());
	}

	public static void main( String[] args) {
		 LocalDateTime verwachteBeginTijd = LocalDateTime.of(2018, Month.JANUARY, 1, 12, 15);
		 Duration verwachteDuur = Duration.of(3, ChronoUnit.HOURS);
		 RivierTocht t = new RivierTocht(verwachteBeginTijd, verwachteDuur);
		// t.start();
		//
		// t.beeindig();
		// Duration d = t.getDuur();
		System.out.println(t);
	}

	public static Tocht create( VoertuigType bootType,  TochtType tochtType,  Duration verwachteDuur) {
		TochtFactory factory = null;
		 ServiceLoader<TochtFactory> serviceLoader = ServiceLoader.load(TochtFactory.class,
				Thread.currentThread().getContextClassLoader());
		for ( TochtFactory tochtFactory : serviceLoader) {
			if (tochtFactory.isTypeFor(bootType, tochtType)) {
				factory = tochtFactory;
				break;
			}
		}
		if (factory == null) {
			throw new IllegalStateException(
					"Sorry geen tochtjes voor het type " + tochtType + " voor de boot " + bootType);
		}

		 Tocht nieuweTocht = factory.create(verwachteDuur);
		return nieuweTocht;
	}
	
	
}