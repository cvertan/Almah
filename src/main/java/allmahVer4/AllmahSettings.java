package allmahVer4;
import java.util.ArrayList;
import java.util.Arrays;
public class AllmahSettings {
protected ArrayList<String> callenderType = new ArrayList<String>( Arrays.asList("Initial Series","Long Count","ISIG","Calendar Round","Tzolk’in","Haab","Supplementary Series",
		"Cycles of 7 and 9 days","Glyph F","Glyph G","Glyph Z","Glyph Y","Lunar Series","Glyph E","Glyph D","Glyph C","Glyph X","Glyph B","Glyph A",
		"Fire event","Cycle of 819 days","Distance Number","Piktun","Bak’tun","K’atun","Tun","Winal","K’in"));

protected ArrayList<String> onomasticsType = new ArrayList<String>( Arrays.asList("Nominal phrase",
		"Personal Name",
		"Epitheton / Title",
		"Place Name",
		"Name for Natural Feature",
		"Name of Cultural Feature",
		"Name of Artificial Feature",
		"Animal Name"
));

	public AllmahSettings() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> getCallenderType(){
		return callenderType;
	}
	public ArrayList<String> getOnomasticsType(){
		return onomasticsType;
	}
}
