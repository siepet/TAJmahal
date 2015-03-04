package src;
import java.util.Random;
public class PsikusImpl implements Psikus {

	/**
	 * przyjmuje liczbe i losowo ucina jej jedna cyfre;
	 * dla liczby jednocyfrowej zwraca null
	 */
	@Override
	public Integer cyfrokrad(Integer liczba) {
		if(liczba.toString().length() == 1){
			return null;
		} else {
			Random random = new Random();
			int randomIndex = random.nextInt(100) % liczba.toString().length();
			//char[] asd1 = new char[liczba.toString().length()];
			String asd = "";
			for(int i = 0; i < liczba.toString().length(); i++){
				if(i != randomIndex){
					asd = asd + liczba.toString().charAt(i);
				} else {
					asd = asd;
				}
			}
			return Integer.parseInt(asd);
		}
	}
	/**
	 * losowo zmienia wystapienie dwoch cyfr w argumencie
	 * dla jednocyfrowej zwraca nieudanypsikusexception
	 */
	@Override
	public Integer hultajchochla(Integer liczba) throws NieudanyPsikusException {
		if(liczba.toString().length() == 1){
			throw new NieudanyPsikusException();
		}
		int indexOne = new Random().nextInt(100) % liczba.toString().length();
		int indexTwo = new Random().nextInt(100) % liczba.toString().length();
		while(indexTwo == indexOne){
			indexTwo = new Random().nextInt(100) % liczba.toString().length();
		}
		String output = "";
		char tmp1, tmp2;
		for(int i = 0; i < liczba.toString().length(); i++){
			tmp1 = liczba.toString().charAt(indexOne);
			tmp2 = liczba.toString().charAt(indexTwo);
			if(i == indexOne){
				output = output + tmp2;
			} else if(i == indexTwo){
				output = output + tmp1;
			} else {
				output = output + liczba.toString().charAt(i);
			}
		}
		
		return Integer.parseInt(output);
	}

	/**
	 * zmiania cyfry w argumencie kolejno:
	 * 3 -> 8
     * 7 -> 1
     * 6 -> 9
     * jesli nie wystepuja to zwraca liczbe
	 */
	@Override
	public Integer nieksztaltek(Integer liczba) {
		int wystepuje = 0;
		String output = "";
		for(int i = 0; i < liczba.toString().length(); i++){
			char c = liczba.toString().charAt(i);
			if(c == '3'){
				output = output + '8';
				wystepuje = 1;
			} else if (c == '7'){
				output = output + '1';
				wystepuje = 1;
			} else if (c == '6') {
				output = output + '9';
				wystepuje = 1;
			} else {
				output = output + c;
			}
		}
		return (wystepuje == 1) ? Integer.parseInt(output) : liczba;
	}

}
