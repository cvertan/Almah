package allmahVer4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class SyntacsValues {

 public Map<String,SyntacticFeature> ABSOLUTIVE_CASE =new HashMap<String, SyntacticFeature>();
 public Map<String,SyntacticFeature> ERGATIVE_CASE =new HashMap<String, SyntacticFeature>();
 public Map<String,SyntacticFeature> INDEPENDENT_OR_DEMONSTRATIVE_PRONOUN =new HashMap<String, SyntacticFeature>();
 public Map<String,SyntacticFeature> NOUN_INFLECTION =new HashMap<String, SyntacticFeature>();
 public Map<String,SyntacticFeature> NOUN_DERIVATION =new HashMap<String, SyntacticFeature>();
 public Map<String,SyntacticFeature> NUMERALS_BOUND =new HashMap<String, SyntacticFeature>();
 public Map<String, SyntacticFeature>THEMATIC_SUFFIXES=new HashMap<String, SyntacticFeature>();
 public Map<String, SyntacticFeature>VERBAL_CLITICS=new HashMap<String, SyntacticFeature>();
 public Map<String, SyntacticFeature>VALENCY_DECREASING_INCREASING_SUFFIXES=new HashMap<String, SyntacticFeature>();
 public Map<String, SyntacticFeature>VERBAL_DERIVATION=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>ADVERBS_LEXICAL=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>ADVERBS_BOUND=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>NOUN_LEXICAL=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>VERB_LEXICAL=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>ADJECTIVE_LEXICAL=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>NUMERAL_LEXICAL=new HashMap<String, SyntacticFeature>();	
 public Map<String, SyntacticFeature>ADJECTIVAL_DERIVATION=new HashMap<String, SyntacticFeature>();
 public Map<String, SyntacticFeature>PREPOSITION=new HashMap<String, SyntacticFeature>();
 public Map<String, SyntacticFeature>PARTICLE=new HashMap<String, SyntacticFeature>();
 
 public SyntacsValues() {
		ABSOLUTIVE_CASE=generateAbsolutiveCase();
		ERGATIVE_CASE=generateErgativeCase();
		INDEPENDENT_OR_DEMONSTRATIVE_PRONOUN=generateDemPronouns();
		NOUN_INFLECTION=generateNounInflection();
		NOUN_DERIVATION=generateNounDerivation();
		NUMERALS_BOUND=generateNumeralsBound();
		THEMATIC_SUFFIXES=generateThematicSuffixes();
		VERBAL_CLITICS=generateVerbalClitics();
		VALENCY_DECREASING_INCREASING_SUFFIXES=generateValencySuffixes();
		VERBAL_DERIVATION=generateVerbalDerivation();
		ADVERBS_LEXICAL=generateAdverbsLexical();
		ADVERBS_BOUND=generateAdverbsBound();
		NOUN_LEXICAL=generateNounLexical();
		VERB_LEXICAL=generateVerbLexical();
		ADJECTIVE_LEXICAL=generateAdjectiveLexical();
		NUMERAL_LEXICAL=generateNumeralLexical();
		ADJECTIVAL_DERIVATION=generateAdjectivalDerivation();
		PREPOSITION=generatePrepositions();
		PARTICLE=generateParticles();
	}
 //SyntacticFeature(String a, ArrayList<FeaturePair> f,ExplanationPair ex)
	//ExplanationPair(ArrayList<String> ex, String expl) 
public Map<String,SyntacticFeature> generateAbsolutiveCase() {
	Map<String,SyntacticFeature> ac =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	//{"-ø", "3s.ABS", "3rd person, singular and plural"}
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","singular plural"));
	ex.add("-ø");
	expl=new ExplanationPair(ex,"3rd person, singular and plural");
	syn=new SyntacticFeature("3s.ABS",f,expl);
	syn.setStem(false);
	ac.put("-ø3s.ABS3rd person, singular and plural",syn);
	
	// {"-een (-Ce-na)", "1s.ABS", "1st person, singular","1","singular"},
	 f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","singular"));
	ex.add("-een (-Ce-na)");
	expl=new ExplanationPair(ex,"1st person, singular");
	syn=new SyntacticFeature("1s.ABS",f,expl);
	syn.setStem(true);
	ac.put("-een (-Ce-na)1s.ABS1st person, singular",syn);

	
	// {"-at? (-Ce-ta)", "2s.ABS", "2nd person, singular"},
	
	 f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","singular"));
	ex.add("-at? (-Ce-ta)");
	expl=new ExplanationPair(ex,"2nd person, singular");
	syn=new SyntacticFeature("2s.ABS",f,expl);
	syn.setStem(true);
	ac.put("-at? (-Ce-ta)2s.ABS2nd person, singular",syn);
	
	//{"-oon (-Co-na)", "1p.ABS", "1st person, plural"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","plural"));
	ex.add("-oon (-Co-na)");
	expl=new ExplanationPair(ex,"1st person, plural");
	syn=new SyntacticFeature("1p.ABS",f,expl);
	syn.setStem(true);
	ac.put("-oon (-Co-na)1p.ABS1st person, plural",syn);
	
	// {"-oob (-Co-ba)", "3p.ABS", "3rd person, plural","3","plural"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","plural"));
	ex.add("-oob (-Co-ba)");
	expl=new ExplanationPair(ex,"3rd person, plural");
	syn=new SyntacticFeature("3p.ABS",f,expl);
	syn.setStem(true);
	ac.put("-oob (-Co-ba)3p.ABS3rd person, plural",syn);
	
	//{"", "2p.ABS", "2nd person, plural","2","plural"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","plural"));
	expl=new ExplanationPair("2nd person, plural");
	syn=new SyntacticFeature("2p.ABS",f,expl);
	syn.setStem(true);
	ac.put("2p.ABS2nd person, plural",syn);
	
	return ac;
}
public Map<String,SyntacticFeature> generateErgativeCase() {
	Map<String,SyntacticFeature> ec =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	//{"u-", "3s.ERG", "3rd person, singular, consonant initial stem"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("u-");
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","singular"));f.add(new FeaturePair("initial stem","consonant"));
	expl=new ExplanationPair(ex,"3rd person, singular, consonant initial stem");
	syn=new SyntacticFeature("3s.ERG",f,expl);
	syn.setStem(true);
	ec.put("u-3s.ERG3rd person, singular, consonant initial stem",syn);
	
	// {"ni-", "1s.ERG", "1st person, singular, consoant initial stem"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("ni-");
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","singular"));f.add(new FeaturePair("initial stem","consonant"));
	expl=new ExplanationPair(ex,"1st person, singular, consoant initial stem");
	syn=new SyntacticFeature("1s.ERG",f,expl);
	syn.setStem(true);
	ec.put("ni-1s.ERG1st person, singular, consoant initial stem",syn);
	
	// {"a-", "2s.ERG", "2nd person, singular, consonant initial stem"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("a-");
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","singular"));f.add(new FeaturePair("initial stem","consonant"));
	expl=new ExplanationPair("2nd person, singular, consonant initial stem");
	syn=new SyntacticFeature("2s.ERG",f,expl);
	syn.setStem(true);
	ec.put("a-2s.ERG2nd person, singular, consonant initial stem",syn);
	
	// {"ka-", "1p.ERG", "1st person, plural, consonant initial stem","1","plural","consonant"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("ka-");
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","plural"));f.add(new FeaturePair("initial stem","consonant"));
	expl=new ExplanationPair(ex,"1st person, plural, consonant initial stem");
	syn=new SyntacticFeature("1p.ERG",f,expl);
	syn.setStem(true);
	ec.put("ka-1p.ERG1st person, plural, consonant initial stem",syn);
	
	//{"", "3p.ERG", "3rd person, plural, consonant initial stem","3","plural","consonnat"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","plural"));f.add(new FeaturePair("initial stem","consonant"));
	expl=new ExplanationPair("3rd person, plural, consonant initial stem");
	syn=new SyntacticFeature("3p.ERG",f,expl);
	syn.setStem(true);
	ec.put("3p.ERG3rd person, plural, consonant initial stem",syn);
	
	// {"", "2p.ERG", "2nd person, plural, consonant initial stem","2","plural","consonant"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","plural"));f.add(new FeaturePair("initial stem","consonant"));
	expl=new ExplanationPair("2nd person, plural, consonant initial stem");
	syn=new SyntacticFeature("2p.ERG",f,expl);
	syn.setStem(true);
	ec.put("2p.ERG2nd person, plural, consonant initial stem",syn);
	
	// {"y-", "3s.ERG", "3rd person, singular, vowel initial stem"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("y-");
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","singular"));f.add(new FeaturePair("initial stem","vowel"));
	expl=new ExplanationPair(ex,"3rd person, singular, vowel initial stem");
	syn=new SyntacticFeature("3s.ERG",f,expl);
	syn.setStem(true);
	ec.put("y-3s.ERG3rd person, singular, vowel initial stem",syn);
	
	// {"w-", "1s.ERG", "1st person, singular, vowel initial stem"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("w-");
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","singular"));f.add(new FeaturePair("initial stem","vowel"));
	expl=new ExplanationPair(ex,"1st person, singular, vowel initial stem");
	syn=new SyntacticFeature("1s.ERG",f,expl);
	syn.setStem(true);
	ec.put("y-1s.ERG1st person, singular, vowel initial stem",syn);
	
	// {"aw- (a-wV-)", "2s.ERG", "2nd person, singular, vowel initial stem"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("aw- (a-wV-)");
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","singular"));f.add(new FeaturePair("initial stem","vowel"));
	expl=new ExplanationPair(ex,"2nd person, singular, vowel initial stem");
	syn=new SyntacticFeature("2s.ERG",f,expl);
	syn.setStem(true);
	ec.put("aw- (a-wV-)2s.ERG2nd person, singular, vowel initial stem",syn);
	
	// {"", "2p.ERG", "2nd person, plural, vowel initial stem"}      
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","plural"));f.add(new FeaturePair("initial stem","vowel"));
	expl=new ExplanationPair("2nd person, plural, vowel initial stem");
	syn=new SyntacticFeature("2s.ERG",f,expl);
	syn.setStem(true);
	ec.put("2p.ERG2nd person, plural, vowel initial stem",syn);
	return ec;
}

public Map<String,SyntacticFeature> generateDemPronouns() {
	Map<String,SyntacticFeature> idp =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	// {"(ha-a)", "3s.DEM", "3rd person, singular, demonstrative pronoun"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("(ha-a)");
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","singular"));
	expl=new ExplanationPair(ex,"3rd person, singular, demonstrative pronoun");
	syn=new SyntacticFeature("3s.DEM",f,expl);
	syn.setStem(true);
	idp.put("(ha-a)3s.DEM3rd person, singular, demonstrative pronoun",syn);
	
	//	 {"(ha-i)", "3s.DEM", "3rd person, singular, demonstrative pronoun","3","singular"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("(ha-i)");
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","singular"));
	expl=new ExplanationPair(ex,"3rd person, singular, demonstrative pronoun");
	syn=new SyntacticFeature("3s.DEM",f,expl);
	syn.setStem(true);
	idp.put("(ha-i)3s.DEM3rd person, singular, demonstrative pronoun",syn);
	
	// {"(ha-o-ba, ha-o-bo)", "3p.DEM", "3rd person, plural, demonstrative pronoun"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("ha-o-ba"); ex.add("ha-o-bo");
	f.add(new FeaturePair("person","third")); f.add(new FeaturePair("number","plural"));
	expl=new ExplanationPair(ex,"3rd person, plural, demonstrative pronoun");
	syn=new SyntacticFeature("3p.DEM",f,expl);
	syn.setStem(true);
	idp.put("(ha-i)3s.DEM3rd person, plural, demonstrative pronoun",syn);
	
	// {"(ha-ta)", "2s.DEM", "2nd person, singular, demonstrative pronoun"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("ha-ta");
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","singular"));
	expl=new ExplanationPair(ex,"2nd person, singular, demonstrative pronoun");
	syn=new SyntacticFeature("2s.DEM",f,expl);
	syn.setStem(true);
	idp.put("(ha-ta)2s.DEM2nd person, singular, demonstrative pronoun",syn);
	
	// {"hiin (hi-na)", "1s.DEM", "1st person, singular, demonstrative pronoun"},
	f=new ArrayList<FeaturePair>();
	 ex=new ArrayList<String>();
	 ex.add("hiin");ex.add("hin-na");
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","singular"));
	expl=new ExplanationPair(ex,"1st person, singular, demonstrative pronoun");
	syn=new SyntacticFeature("1s.DEM",f,expl);
	syn.setStem(true);
	idp.put("hiin (hi-na)1s.DEM1st person, singular, demonstrative pronoun",syn);
	
	//{"", "1p.DEM", "1st person, plural, demonstrative pronoun"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("person","first")); f.add(new FeaturePair("number","plural"));
	expl=new ExplanationPair("1st person, plural, demonstrative pronoun");
	syn=new SyntacticFeature("1p.DEM",f,expl);
	syn.setStem(true);
	idp.put("1p.DEM1st person, plural, demonstrative pronoun",syn);
	
	//{"", "2p.DEM", "2nd person, plural, demonstrative pronoun"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("person","second")); f.add(new FeaturePair("number","plural"));
	expl=new ExplanationPair("2nd person, plural, demonstrative pronoun");
	syn=new SyntacticFeature("2p.DEM",f,expl);
	syn.setStem(true);
	idp.put("2p.DEM2nd person, plural, demonstrative pronoun",syn);
	
	return idp;
}
public Map<String,SyntacticFeature> generateNounInflection() {
	Map<String,SyntacticFeature> ni =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	// {"", "RELZ", "relationalizer (inalienable possession)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","relationalizer"));f.add(new FeaturePair("posession_type","inalienable"));
	expl=new ExplanationPair("relationalizer (inalienable possession)");
	syn=new SyntacticFeature("RELZ",f,expl);
	syn.setStem(true);
	ni.put("RELZrelationalizer (inalienable possession)",syn);
	
	//{"", "ABS", "absoluble, unpossessed form of body part","absoluble","unpossessed","body part"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","absoluble"));f.add(new FeaturePair("posession_type","unpossessed"));f.add(new FeaturePair("posession_object","body part"));
	expl=new ExplanationPair("absoluble, unpossessed form of body part");
	syn=new SyntacticFeature("ABS",f,expl);
	syn.setStem(true);
	ni.put("ABSabsoluble, unpossessed form of body part",syn);
	
	// {"-il", "RELZ", "relationalizer (inalienable possession, u baah-il aan)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-il");
	f.add(new FeaturePair("type","relationalizer"));f.add(new FeaturePair("posession_type","inalienable"));
	expl=new ExplanationPair(ex,"relationalizer (inalienable possession, u baah-il aan)");
	syn=new SyntacticFeature("RELZ",f,expl);
	syn.setStem(true);
	ni.put("-ilRELZrelationalizer (inalienable possession, u baah-il aan)",syn);
	
	// {"-el", "RELZ", "relationalizer (inalienable possession of body, substances, blood, bones, skin and hair (e.g. u bak-el)","relationalizer","inalienable","body substances, blood, skin, hair"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-el");
	f.add(new FeaturePair("type","relationalizer"));f.add(new FeaturePair("posession_type","inalienable"));f.add(new FeaturePair("posession_object","body, substances, bones, blood, skin, hair"));
	expl=new ExplanationPair(ex,"relationalizer (inalienable possession, u baah-il aan)");
	syn=new SyntacticFeature("RELZ",f,expl);
	syn.setStem(true);
	ni.put("-elRELZrelationalizer (inalienable possession of body, substances, blood, bones, skin and hair (e.g. u bak-el)",syn);
	
	// {"-is", "ABS", "absoluble, unpossessed form of body part (e.g. ohl-is)",} 
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-is");
	f.add(new FeaturePair("type","absolble"));f.add(new FeaturePair("posession_type","unposessed")); f.add(new FeaturePair("posession_object","body part"));
	expl=new ExplanationPair(ex,"relationalizer (inalienable possession, u baah-il aan)");
	syn=new SyntacticFeature("ABS",f,expl);
	syn.setStem(true);
	ni.put("-isABSabsoluble, unpossessed form of body part (e.g. ohl-is)",syn);
	
	
	
	return ni;
}
	
public Map<String,SyntacticFeature> generateNounDerivation() {
	Map<String,SyntacticFeature> nd =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	//{"", "ABSTR", "abstract noun"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","abtract noun"));
	expl=new ExplanationPair("abstract noun");
	syn=new SyntacticFeature("ABSTR",f,expl);
	syn.setStem(true);
	nd.put("ABSTRabstract noun",syn);
	//	{"-il", "ABSTR", "abstract noun (e.g. ajaw-il)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-il");
	f.add(new FeaturePair("type","abtract noun"));
	expl=new ExplanationPair(ex,"abstract noun (e.g. ajaw-il)");
	syn=new SyntacticFeature("ABSTR",f,expl);
	syn.setStem(true);
	nd.put("-ilABSTRabstract noun (e.g. ajaw-il)",syn);
	// {"-lel", "ABSTR", "abstract noun (e.g. ajaw-lel)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-lel");
	f.add(new FeaturePair("type","abtract noun"));
	expl=new ExplanationPair(ex,"abstract noun (e.g. ajaw-lel)");
	syn=new SyntacticFeature("ABSTR",f,expl);
	syn.setStem(true);
	nd.put("-lelABSTRabstract noun (e.g. ajaw-lel)",syn);
	// {"-lil", "ABSTR", "abstract noun (e.g. ajaw-lil)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-lil");
	f.add(new FeaturePair("type","abtract noun"));
	expl=new ExplanationPair(ex,"abstract noun (e.g. ajaw-lil)");
	syn=new SyntacticFeature("ABSTR",f,expl);
	syn.setStem(true);
	nd.put("-lilABSTRabstract noun (e.g. ajaw-lil)",syn);
	//{"-Vl", "ABSTR", "abstract noun (e.g. te'-el)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-Vl");
	f.add(new FeaturePair("type","abtract noun"));
	expl=new ExplanationPair(ex,"abstract noun (e.g. te'-el)");
	syn=new SyntacticFeature("ABSTR",f,expl);
	syn.setStem(true);
	nd.put("-VlABSTRabstract noun (e.g. te'-el)",syn);
	//{"-aal", "ABSTR", "noun from noun (e.g. tz'ihb-aal)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-aal");
	f.add(new FeaturePair("type","abtract noun"));f.add(new FeaturePair("subtype","noun from noun"));
	expl=new ExplanationPair(ex,"noun from noun (e.g. tz'ihb-aal)");
	syn=new SyntacticFeature("ABSTR",f,expl);
	syn.setStem(true);
	nd.put("-aalABSTRnoun from noun (e.g. tz'ihb-aal)",syn);
	
	// {"", "NMLZ", "abstract noun"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","nominalizer"));
	expl=new ExplanationPair("nominalizer");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("NMLZnominalizer",syn);
	
	//	 {"-aj", "NMLZ", "nominalizer (e.g. tek'-aj, pas-aj)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-aj");
	f.add(new FeaturePair("type","nominalizer"));
	expl=new ExplanationPair(ex, "nominalizer (e.g. tek'-aj, pas-aj)");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("-ajNMLZnominalizer (e.g. tek'-aj, pas-aj)",syn);
	
	// {"-il", "NMLZ", "nominalizer (compound verbs, e.g. u k'altuun-il)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-il");
	f.add(new FeaturePair("type","nominalizer"));f.add(new FeaturePair("subtype","compound verbs"));
	expl=new ExplanationPair(ex, "nominalizer (compound verbs, e.g. u k'altuun-il)");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("-ilNMLZnominalizer (compound verbs, e.g. u k'altuun-il)",syn);
	
	// {"-el", "NMLZ", "nominalizer, gerundival nouns (e.g. y-och-el, u joy-el)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-el");
	f.add(new FeaturePair("type","nominalizer"));f.add(new FeaturePair("subtype","gerundival nouns"));
	expl=new ExplanationPair(ex, "nominalizer, gerundival nouns (e.g. y-och-el, u joy-el)");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("-elNMLZnominalizer, gerundival nouns (e.g. y-och-el, u joy-el)",syn);
	
	//	 {"-an", "NMLZ", "nominalizer (few nouns only)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-an");
	f.add(new FeaturePair("type","nominalizer"));f.add(new FeaturePair("subtype","few nouns"));
	expl=new ExplanationPair(ex, "nominalizer (few nouns only)");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("-anNMLZnominalizer (few nouns only)",syn);
	
	 //{"-Vn", "NMLZ", "nominalizer (e.g. pet-en)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-Vn");
	f.add(new FeaturePair("type","nominalizer"));
	expl=new ExplanationPair(ex, "nominalizer (e.g. pet-en)");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("-VnNMLZnominalizer (e.g. pet-en)",syn);
	
	 // {"-Vt", "NMLZ", "nominalizer (e.g. muk'-ut)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-Vt");
	f.add(new FeaturePair("type","nominalizer"));
	expl=new ExplanationPair(ex, "nominalizer (e.g. muk'-ut)");
	syn=new SyntacticFeature("NMLZ",f,expl);
	syn.setStem(true);
	nd.put("-VtNMLZnominalizer (e.g. muk'-ut)",syn);
	
	// {"-ib'", "NMLZ.INSTR", "instrumental noun from verb (instruments and places)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();;
	 ex.add("-ib");
	f.add(new FeaturePair("type","nominalizer instrumental"));f.add(new FeaturePair("instrumental","yes"));f.add(new FeaturePair("subtype","noun from verb"));
	expl=new ExplanationPair(ex, "instrumental noun from verb (instruments and places)");
	syn=new SyntacticFeature("NMLZ.INSTR",f,expl);
	syn.setStem(true);
	nd.put("-ibNMLZ.INSTRinstrumental noun from verb (instruments and places)",syn);
	
	//	 {"-Vl", "NMLZ.VN", "nominalizer of verb (e.g. chok-ol"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-Vl");
	f.add(new FeaturePair("type","nominalizer"));f.add(new FeaturePair("subtype","of verb"));
	expl=new ExplanationPair(ex, "nominalizer of verb (e.g. chok-ol)");
	syn=new SyntacticFeature("NMLZ.VN",f,expl);
	syn.setStem(true);
	nd.put("-VlNMLZ.VNnominalizer of verb (e.g. chok-ol)",syn);
	
	//	 {"-Vl", "NMLZ.VN", "derives noun with instrumental meanings from verb (pok-ol)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-Vl");
	f.add(new FeaturePair("type","nominalizer"));f.add(new FeaturePair("subtype","derives noun with instrumental meanings from verb"));
	expl=new ExplanationPair(ex, "derives noun with instrumental meanings from verb (pok-ol)");
	syn=new SyntacticFeature("NMLZ.VN",f,expl);
	syn.setStem(true);
	nd.put("-VlNMLZ.VNderives noun with instrumental meanings from verb (pok-ol)",syn);
	
	//	 {"-VVb (-Ca-bi, Cu-bi)", "NMLZ.INSTR", "suffix of agentive noun (from verbs)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-VVb");  ex.add("-Ca-bi"); ex.add("-Cu-bi");
	f.add(new FeaturePair("type","nominalizer instrumental"));f.add(new FeaturePair("subtype","noun from verb"));f.add(new FeaturePair("subtype","suffix of agentive noun"));
	expl=new ExplanationPair(ex, "suffix of agentive noun (from verbs)");
	syn=new SyntacticFeature("NMLZ.INSTR",f,expl);
	syn.setStem(true);
	nd.put("-VVb (-Ca-bi, Cu-bi)NMLZ.INSTRsuffix of agentive noun (from verbs)",syn);
	
	// {"", "LOC", "locative noun"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","locative noun"));
	expl=new ExplanationPair("locative noun");
	syn=new SyntacticFeature("LOC",f,expl);
	syn.setStem(true);
	nd.put("LOClocative noun",syn);
	
	//{"-Vl", "LOC", "place-name from noun, local case relator (where X abounds)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-Vl");
	f.add(new FeaturePair("type","locative noun"));	f.add(new FeaturePair("subtype","place-name from noun, local case relator "));
	expl=new ExplanationPair(ex,"place-name from noun, local case relator (where X abounds)");
	syn=new SyntacticFeature("LOC",f,expl);
	syn.setStem(true);
	nd.put("-VlLOCplace-name from noun, local case relator (where X abounds)",syn);
	
	//	 {"-nal", "LOC", "locative suffix"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-nal");
	f.add(new FeaturePair("type","locative noun"));	f.add(new FeaturePair("subtype","locative suffix"));
	expl=new ExplanationPair(ex,"locative suffix");
	syn=new SyntacticFeature("LOC",f,expl);
	syn.setStem(true);
	nd.put("-nalLOClocative suffix",syn);
		 
	// {"", "AGT", "agentive noun"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","agentive noun"));
	expl=new ExplanationPair("agentive noun");
	syn=new SyntacticFeature("AGT",f,expl);
	syn.setStem(true);
	nd.put("AGTagentive noun",syn);
	
	// {"-a", "AGT", "suffix of agentive noun (from verbs and nouns, hul-a)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-a");
	f.add(new FeaturePair("type","agentive noun"));f.add(new FeaturePair("subtype","suffix of"));
	expl=new ExplanationPair(ex,"suffix of agentive noun (from verbs and nouns, hul-a)");
	syn=new SyntacticFeature("AGT",f,expl);
	syn.setStem(true);
	nd.put("-aAGTsuffix of agentive noun (from verbs and nouns, hul-a)",syn);
	
	//	 {"aj-", "AGT", "prefix of agentive noun (from verb and nouns)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("aj-");
	f.add(new FeaturePair("type","agentive noun"));f.add(new FeaturePair("subtype","prefix of"));
	expl=new ExplanationPair(ex,"prefix of agentive noun (from verb and nouns)");
	syn=new SyntacticFeature("AGT",f,expl);
	syn.setStem(true);
	nd.put("aj-AGTprefix of agentive noun (from verb and nouns)",syn);
	
	//	 {"-oom", "AGT", "suffix of agentive noun (from verbs)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-oom");
	f.add(new FeaturePair("type","agentive noun"));f.add(new FeaturePair("subtype","suffix of"));
	expl=new ExplanationPair(ex,"suffix of agentive noun (from verbs)");
	syn=new SyntacticFeature("AGT",f,expl);
	syn.setStem(true);
	nd.put("-oomAGTsuffix of agentive noun (from verbs)",syn);
	
	//{"", "INSTR", "instrumental noun"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","instrumental noun"));
	expl=new ExplanationPair("instrumental noun");
	syn=new SyntacticFeature("INSTR",f,expl);
	syn.setStem(true);
	nd.put("INSTRinstrumental noun",syn);
	
	// {"", "F", "female proclitic"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","female proclitic"));
	expl=new ExplanationPair("female proclitic");
	syn=new SyntacticFeature("F",f,expl);
	syn.setStem(true);
	nd.put("Ffemale proclitic",syn);
	
	//	 {"ix-", "F", "female proclitic (from verb and nouns)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("ix-");
	f.add(new FeaturePair("type","female proclitic"));f.add(new FeaturePair("subtype","from verb and nouns"));
	expl=new ExplanationPair(ex,"female proclitic (from verb and nouns)");
	syn=new SyntacticFeature("F",f,expl);
	syn.setStem(true);
	nd.put("ix-Ffemale proclitic (from verb and nouns)",syn);
	
	// {"", "PL", "collective nouns, plural"},  
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","collective noun"));
	expl=new ExplanationPair("collective nouns, plural");
	syn=new SyntacticFeature("PL",f,expl);
	syn.setStem(true);
	nd.put("PLcollective nouns, plural",syn);
	
	//		 {"-taak (-ta-ki)", "PL", "collective noun (e.g. ajaw-taak)"},
	f=new ArrayList<FeaturePair>();
	ex=new ArrayList<String>();
	 ex.add("-taak "); ex.add("-ta-ki ");
	f.add(new FeaturePair("type","collective noun"));
	expl=new ExplanationPair(ex,"collective noun (e.g. ajaw-taak)");
	syn=new SyntacticFeature("PL",f,expl);
	syn.setStem(true);
	nd.put("-taak (-ta-ki)PLcollective noun (e.g. ajaw-taak)",syn);
	
	// {"", "VN", "verbal noun"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","verbal noun"));
	expl=new ExplanationPair("verbal noun");
	syn=new SyntacticFeature("VN",f,expl);
	syn.setStem(true);
	nd.put("VNverbal noun",syn);
	
	
// {"+", "+", "composite noun, compound nouns and verbs (k'intuun, elk'in)"}
		f=new ArrayList<FeaturePair>();
		ex=new ArrayList<String>();
		 ex.add("+"); 
		f.add(new FeaturePair("type","composite noun"));f.add(new FeaturePair("subtype","compound nouns and verbs"));
		expl=new ExplanationPair(ex,"composite noun, compound nouns and verbs (k'intuun, elk'in)");
		syn=new SyntacticFeature("+",f,expl);
		syn.setStem(true);
		nd.put("++composite noun, compound nouns and verbs (k'intuun, elk'in)",syn);
	
	return nd;
}

public Map<String,SyntacticFeature> generateNumeralsBound() {
	Map<String,SyntacticFeature> nb =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"-ij (-ji)", "ADVLZ.FUT", "adverbializer, counting of time periods (in N days)"}, 
	f.add(new FeaturePair("type","adverbializer")); f.add(new FeaturePair("function","counting of time periods future"));
	ex.add("-ij");ex.add("-ji");
	expl=new ExplanationPair(ex,"adverbializer, counting of time periods (in N days)");
	syn=new SyntacticFeature("ADVLZ.FUT",f,expl);
	syn.setStem(true);
	nb.put("-ij (-ji)ADVLZ.FUTadverbializer, counting of time periods (in N days)",syn);
	
	// {"-ij-iiy (-ji-ya)", "ADVLZ.ANT", "adverbializer, counting of time periods from the past (N days later)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverbializer")); f.add(new FeaturePair("function","counting of time periods past"));
	ex=new ArrayList<String>();
	ex.add("-ij-iiy");ex.add("-ji-ya");
	expl=new ExplanationPair(ex,"counting of time periods from the past (N days later)");
	syn=new SyntacticFeature("ADVLZ.ANT",f,expl);
	syn.setStem(true);
	nb.put("-ij-iiy (-ji-ya)ADVLZ.ANTadverbializer, counting of time periods from the past (N days later)",syn);
	
	// {"-lat, -he'w, -tal", "CLF", "count of days"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of days"));
	ex=new ArrayList<String>();
	ex.add("-lat");ex.add("-he'w");ex.add("tal");
	expl=new ExplanationPair(ex,"count of days");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-lat, -he'w, -talCLFcount of days",syn);
	
	// {"-he'n", "CLF", "count of days"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of days"));
	ex=new ArrayList<String>();
	ex.add("-he'n");
	expl=new ExplanationPair(ex,"count of days");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-lat, -he'w, -talCLFcount of days",syn);
	
	// {"-bix", "CLF", "count of either 5 or 7 days"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of either 5 or 7 days"));
	ex=new ArrayList<String>();
	ex.add("-bix");
	syn.setStem(true);
	expl=new ExplanationPair(ex,"count of either 5 or 7 days");
	syn=new SyntacticFeature("CLF",f,expl);
	nb.put("-bixCLFcount of either 5 or 7 days",syn);
	
	// {"-te'", "CLF", "default numeral classifier"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","default numeral classifier"));
	ex=new ArrayList<String>();
	ex.add("-te");
	expl=new ExplanationPair(ex,"default numeral classifier");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-teCLFdefault numeral classifier",syn);
	
	// {"-tz'ak","CLF", "count of successors"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of successors"));
	ex=new ArrayList<String>();
	ex.add("-tz'ak");
	expl=new ExplanationPair(ex,"count of successors");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-tz'akCLFcount of successors",syn);
	
	// {"-pis", "CLF", "count of years"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of years"));
	ex=new ArrayList<String>();
	ex.add("-pis");
	expl=new ExplanationPair(ex,"count of years");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-pisCLFcount of years",syn);
	
	// {"-kob", "CLF", "count of twins"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of twins"));
	ex=new ArrayList<String>();
	ex.add("-kob");
	expl=new ExplanationPair(ex,"count of twins");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-kobCLFcount of twins",syn);
	
	//{"-kul", "CLF", "count of stones and years"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of stones and years"));
	ex=new ArrayList<String>();
	ex.add("-kul");
	expl=new ExplanationPair(ex,"count of stones and years");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-kulCLFcount of stones and years",syn);
	
	//{"-mul", "CLF", "count of stacked objects"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of stacked objects"));
	ex=new ArrayList<String>();
	ex.add("-mul");
	expl=new ExplanationPair(ex,"count of stacked objects");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-mulCLFcount of stacked objects",syn);
	
	//{"-nak", "CLF", "count of lower titles"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of lower titles"));
	ex=new ArrayList<String>();
	ex.add("-nak");
	expl=new ExplanationPair(ex,"count of lower titles");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-nakCLFcount of lower titles",syn);
	
	//{"-pet", "CLF", "count of circular objects"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of circular objects"));
	ex=new ArrayList<String>();
	ex.add("-pet");
	expl=new ExplanationPair(ex,"count of circular objects");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-petCLFcount of circular objects",syn);
	
	//{"-tikil", "CLF", "count of people"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of people"));
	ex=new ArrayList<String>();
	ex.add("-tikil");
	expl=new ExplanationPair(ex,"count of people");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-tikilCLFcount of people",syn);
	
	// {"-tuk", "CLF", "count of stacks"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of stacks"));
	ex=new ArrayList<String>();
	ex.add("-tuk");
	expl=new ExplanationPair(ex,"count of stacks");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-tukCLFcount of stacks",syn);
	
	// {"-nahb", "CLF", "count of handspans"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","classifier")); f.add(new FeaturePair("function","count of handspans"));
	ex=new ArrayList<String>();
	ex.add("-nahb");
	expl=new ExplanationPair(ex,"count of handspans");
	syn=new SyntacticFeature("CLF",f,expl);
	syn.setStem(true);
	nb.put("-nahbCLFcount of handspans",syn);
	
	return nb;
}

public Map<String,SyntacticFeature> generateThematicSuffixes() {
	Map<String,SyntacticFeature> ts =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	//{"-i", "THM", "thematic suffix for intransitive verbs"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intransitive"));f.add(new FeaturePair("subtype","thematic suffix"));
	ex=new ArrayList<String>();
	ex.add("-i");
	expl=new ExplanationPair(ex,"thematic suffix for intransitive verbs");
	syn=new SyntacticFeature("THM",f,expl);
	ts.put("-iTHMthematic suffix for intransitive verbs",syn);
	
	// {"-Vw", "TR", "transitive verbs"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","transitive"));
	ex=new ArrayList<String>();
	ex.add("-Vw");
	expl=new ExplanationPair(ex,"transitive verbs");
	syn=new SyntacticFeature("TR",f,expl);
	ts.put("-VwTRtransitive verbs",syn);
	
	// {"-VVn", "TR.D", "derived transitive verbs"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","transitive"));f.add(new FeaturePair("subtype","derived"));
	ex=new ArrayList<String>();
	ex.add("-VVn");
	expl=new ExplanationPair(ex,"derived transitive verbs");
	syn=new SyntacticFeature("TR.D",f,expl);
	syn.setStem(true);
	ts.put("-VVnTR.Dderived transitive verbs",syn);
	
	/*	 {"-i", "INTR", "intransitive verbs"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intransitive"));
	ex=new ArrayList<String>();
	ex.add("-i");
	expl=new ExplanationPair(ex,"intransitive verbs");
	syn=new SyntacticFeature("INTR",f,expl);
	ts.put("-iINTRintransitive verbs",syn);*/
	
	// {"-el", "INTR", "intransitive verbs"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intransitive"));
	ex=new ArrayList<String>();
	ex.add("-el");
	expl=new ExplanationPair(ex,"intransitive verbs");
	syn=new SyntacticFeature("INTR",f,expl);
	syn.setStem(true);
	ts.put("-elINTRintransitive verbs",syn);
	
	// {"-aj", "INTR.D", "derived intransitive verbs (e.g. ahk't-aj, joy-aj, tup-aj, k'alhun-aj)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intransitive"));f.add(new FeaturePair("subtype","derived"));
	ex=new ArrayList<String>();
	ex.add("-aj");
	expl=new ExplanationPair(ex,"intransitve verbs");
	syn=new SyntacticFeature("INTR.D",f,expl);
	syn.setStem(true);
	ts.put("-ajINTR.Dderived intransitive verbs (e.g. ahk't-aj, joy-aj, tup-aj, k'alhun-aj)",syn);
	
	//{"-iij", "INTR.D.", "derived intransitive verbs (e.g. witz-iij, tak-iij"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intransitive"));f.add(new FeaturePair("subtype","derived"));
	ex=new ArrayList<String>();
	ex.add("-iij");
	expl=new ExplanationPair(ex,"intransitive verbs");
	syn=new SyntacticFeature("INTR.D",f,expl);
	syn.setStem(true);
	ts.put("-iijINTR.Dderived intransitive verbs (e.g. witz-iij, tak-iij}",syn);
	
	// {"-laj", "POS", "positional verbs (e.g. chum-laj"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","positional"));
	ex=new ArrayList<String>();
	ex.add("-laj");
	expl=new ExplanationPair(ex,"positional verbs");
	syn=new SyntacticFeature("POS",f,expl);
	syn.setStem(true);
	ts.put("-lajPOSpositional verbs (e.g. chum-laj",syn);
	
	// {"-waan", "POS", "positional verbs (e.g. chum-waan)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","positional"));
	ex=new ArrayList<String>();
	ex.add("-waan");
	expl=new ExplanationPair(ex,"positional verbs");
	syn=new SyntacticFeature("POS",f,expl);
	syn.setStem(true);
	ts.put("-waanPOSpositional verbs (e.g. chum-waan)",syn);
	
	
	//{"-laj", "AFF", "affective verbs (e.g. bah-laj, yuhk-laj)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","affectional"));
	ex=new ArrayList<String>();
	ex.add("-laj");
	expl=new ExplanationPair(ex,"affectional verbs");
	syn=new SyntacticFeature("AFF",f,expl);
	syn.setStem(true);
	ts.put("-lajAFFaffective verbs (e.g. bah-laj, yuhk-laj)",syn);
	
	// {"-l-", "POS", "positional verbs (e.g. chum-l-iiy)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","positional"));
	ex=new ArrayList<String>();
	ex.add("-l-");
	expl=new ExplanationPair(ex,"positional verbs");
	syn=new SyntacticFeature("POS",f,expl);
	syn.setStem(true);
	ts.put("-l-POSpositional verbs (e.g. chum-l-iiy)",syn);
	
	// {"-VVj", "PRF", "perfect"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","perfect"));
	ex=new ArrayList<String>();
	ex.add("-VVj-");
	expl=new ExplanationPair(ex,"perfect");
	syn=new SyntacticFeature("PRF",f,expl);
	syn.setStem(true);
	ts.put("-VVjPRFperfect",syn);
	
	// {"-Vk", "MOD.OPT.V.INTR", "optative"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","optative"));
	ex=new ArrayList<String>();
	ex.add("-Vk-");
	expl=new ExplanationPair(ex,"optative");
	syn=new SyntacticFeature("MOD.OPT.V.INTR",f,expl);
	syn.setStem(true);
	ts.put("-VkMOD.OPT.V.INTRoptative",syn);
	
	// {"-V", "IMP", "imperatives from CVC transitive verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","imperative"));f.add(new FeaturePair("subtype","from CVC transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-V");
	expl=new ExplanationPair(ex,"imperatives from CVC transitive verbal stems");
	syn=new SyntacticFeature("IMP",f,expl);
	syn.setStem(true);
	ts.put("-VIMPimperatives from CVC transitive verbal stems",syn);
	
	// {"-oom", "FUT", "unavoidable future (prophetic future)"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","future"));f.add(new FeaturePair("subtype","unavoidable"));
	ex=new ArrayList<String>();
	ex.add("-oom");
	expl=new ExplanationPair(ex,"unavoidable future (prophetic future)");
	syn=new SyntacticFeature("FUT",f,expl);
	syn.setStem(true);
	ts.put("-oomFUTunavoidable future (prophetic future)",syn);
	return ts;
}
public Map<String,SyntacticFeature> generateVerbalClitics() {
	Map<String,SyntacticFeature> vc =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"-iiy (Vi-ya)", "ANT", "anterior, deictic clitic, mostly referring to events in the past"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","anterior"));f.add(new FeaturePair("subtype","deictic"));
	ex=new ArrayList<String>();
	ex.add("-iiy");ex.add("Vi-ya");
	expl=new ExplanationPair(ex,"anterior, deictic clitic, mostly referring to events in the past");
	syn=new SyntacticFeature("ANT",f,expl);
	syn.setStem(true);
	vc.put("-iiy (Vi-ya)ANTanterior, deictic clitic, mostly referring to events in the past",syn);
	
	//{"i-", "THEN", "then"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","then"));
	ex=new ArrayList<String>();
	ex.add("i-");
	expl=new ExplanationPair(ex,"then");
	syn=new SyntacticFeature("THEN",f,expl);
	syn.setStem(true);
	vc.put("i-THENthen",syn);
	
	//{"wa-", "PROG", "progressive/durative aspect"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","progressive"));f.add(new FeaturePair("subtype","durative"));
	ex=new ArrayList<String>();
	ex.add("wa-");
	expl=new ExplanationPair(ex,"progressive/durative aspect");
	syn=new SyntacticFeature("PROG",f,expl);
	syn.setStem(true);
	vc.put("wa-PROGprogressive/durative aspect",syn);
	
	//{"xa-", "ALREADY", "already"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","already"));
	ex=new ArrayList<String>();
	ex.add("xa-");
	expl=new ExplanationPair(ex,"already");
	syn=new SyntacticFeature("ALREADY",f,expl);
	syn.setStem(true);
	vc.put("xa-ALREADYalready",syn);
	
	//{"ma'", "NEG", "negative particle"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","negative particle"));
	ex=new ArrayList<String>();
	ex.add("ma'");
	expl=new ExplanationPair(ex,"negative particle");
	syn=new SyntacticFeature("NEG",f,expl);
	syn.setStem(true);
	vc.put("ma'NEGnegative particle",syn);
	
	//{"", "CLT", "general clitic"}
	
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","general"));
	expl=new ExplanationPair("general clitic");
	syn=new SyntacticFeature("CLT",f,expl);
	syn.setStem(true);
	vc.put("CLTgeneralclitic",syn);
	return vc;
}

public Map<String,SyntacticFeature> generateValencySuffixes() {
	Map<String,SyntacticFeature> vs =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"", "PAS", "passive"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","passive"));
	expl=new ExplanationPair("passive");
	syn=new SyntacticFeature("PAS",f,expl);
	syn.setStem(false);
	vs.put("PASpassive",syn);
	
	// {"", "MED", "mediopassive, middle voice"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","mediopassive"));f.add(new FeaturePair("subtype","middle voice"));
	expl=new ExplanationPair("mediopassive, middle voice");
	syn=new SyntacticFeature("MED",f,expl);
	syn.setStem(true);
	vs.put("MEDmediopassive, middle voice",syn);
	
	// {"", "AP", "antipassive"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","antipassive"));
	expl=new ExplanationPair("antipassive");
	syn=new SyntacticFeature("AP",f,expl);
	syn.setStem(true);
	vs.put("APantipassive",syn);
	
	// {"-h-", "PAS", "infix of passives fromCVC transitiv verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","infix of passives"));f.add(new FeaturePair("subtype","fromCVC transitiv verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-h-'");
	expl=new ExplanationPair(ex, "passive");
	syn=new SyntacticFeature("PAS",f,expl);
	syn.setStem(false);
	vs.put("-h-PASinfix of passives fromCVC transitiv verbal stems",syn);
	
	// {"-n-", "PAS.D", "suffix of passives from derived transitive verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","suffix of passives"));f.add(new FeaturePair("subtype","from derived transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-n-'");
	expl=new ExplanationPair(ex, "suffix of passives from derived transitive verbal stems");
	syn=new SyntacticFeature("PAS.D",f,expl);
	syn.setStem(true);
	vs.put("-n-PAS.Dsuffix of passives from derived transitive verbal stems",syn);
	
	// {"-w-", "PAS.D", "suffix of passive from derived transitive verbal stems (dialectical variant)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","suffix of passives"));f.add(new FeaturePair("subtype","derived transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-w-'");
	expl=new ExplanationPair(ex, "suffix of passive from derived transitive verbal stems (dialectical variant)");
	syn=new SyntacticFeature("PAS.D",f,expl);
	syn.setStem(true);
	vs.put("-w-PAS.Dsuffix of passive from derived transitive verbal stems (dialectical variant)",syn);
	
	// {"-Vb-", "PAS.D", "passive (Yucatecan)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","passive"));f.add(new FeaturePair("subtype","Yucatecan"));
	ex=new ArrayList<String>();
	ex.add("-Vb-'");
	expl=new ExplanationPair(ex, "passive (Yucatecan)");
	syn=new SyntacticFeature("PAS.D",f,expl);
	syn.setStem(true);
	vs.put("-Vb-PAS.Dpassive (Yucatecan)",syn);
	
	// {"-VVy", "MED", "mediopassive; middle voice (state of change verbs)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","mediopassive"));f.add(new FeaturePair("subtype","middle voice"));f.add(new FeaturePair("subtype","state of change verbs"));
	ex=new ArrayList<String>();
	ex.add("-VVy-'");
	expl=new ExplanationPair(ex, "mediopassive; middle voice (state of change verbs)");
	syn=new SyntacticFeature("MED",f,expl);
	syn.setStem(true);
	vs.put("-VVyMEDmediopassive; middle voice (state of change verbs)",syn);
	
	// {"-ey", "MED", "mediopassive; middle voice (state of change verbs) of glottal stop initial verb stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","mediopassive"));f.add(new FeaturePair("subtype","middle voice"));f.add(new FeaturePair("subtype","state of change verbs"));
	f.add(new FeaturePair("subtype"," of glottal stop initial verb stems"));
	ex=new ArrayList<String>();
	ex.add("-ey'");
	expl=new ExplanationPair(ex, "mediopassive; middle voice (state of change verbs) of glottal stop initial verb stems");
	syn=new SyntacticFeature("MED",f,expl);
	syn.setStem(true);
	vs.put("-eyMEDmediopassive; middle voice (state of change verbs) of glottal stop initial verb stems",syn);
	
	// {"-Vw", "AP", "antipassives from CVC transitive verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","antipassive"));f.add(new FeaturePair("subtype","from CVC transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-Vw'");
	expl=new ExplanationPair(ex, "antipassives from CVC transitive verbal stems");
	syn=new SyntacticFeature("AP",f,expl);
	syn.setStem(true);
	vs.put("-VwAPantipassives from CVC transitive verbal stems",syn);
	
	// {"-(o)n, -n-", "AP", "antipassives from non-CVC transitive verbal stems (e.g. ahk'-n-om) "},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","antipassive"));f.add(new FeaturePair("subtype","from non-CVC transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-(o)n'");ex.add("-n-'");
	expl=new ExplanationPair(ex, "antipassives from non-CVC transitive verbal stems (e.g. ahk'-n-om) ");
	syn=new SyntacticFeature("AP",f,expl);
	syn.setStem(true);
	vs.put("-(o)n, -n-APantipassives from non-CVC transitive verbal stems (e.g. ahk'-n-om) ",syn);
	
	// {"-ø", "AP.D", "antipassives from derived transitive verbal stems"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","antipassive"));f.add(new FeaturePair("subtype"," from derived transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-ø'");
	expl=new ExplanationPair(ex, "antipassives from derived transitive verbal stems");
	syn=new SyntacticFeature("AP.D",f,expl);
	syn.setStem(false);
	vs.put(" -øAP.Dantipassives from derived transitive verbal stems",syn);
	return vs;
}

public Map<String,SyntacticFeature> generateVerbalDerivation() {
	Map<String,SyntacticFeature> vd =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"", "CAUS", "causative"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","causative"));
	expl=new ExplanationPair("causative");
	syn=new SyntacticFeature("CAUS",f,expl);
	syn.setStem(true);
	vd.put("CAUScausative",syn);
	
	//{"", "INCH", "inchoative"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","inchotive"));
	expl=new ExplanationPair("inchoative");
	syn=new SyntacticFeature("INCH",f,expl);
	syn.setStem(true);
	vd.put("INCHinchoative",syn);
	
	// {"-b'u(-*b'a)", "POS.CAUS", "causatives from positional verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","causatives"));f.add(new FeaturePair("subtype","from positional verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-b'u");ex.add("-*b'a");
	expl=new ExplanationPair(ex, "causatives from positional verbal stems");
	syn=new SyntacticFeature("POS.CAUS",f,expl);
	syn.setStem(true);
	vd.put("-b'u(-*b'a)POS.CAUScausatives from positional verbal stems",syn);
	
	//{"-es", "INTR.CAUS", "causatives from other intransitive verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","causatives"));f.add(new FeaturePair("subtype","from other intransitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-es");
	expl=new ExplanationPair(ex, "causatives from other intransitive verbal stems");
	syn=new SyntacticFeature("INTR.CAUS",f,expl);
	syn.setStem(true);
	vd.put("-esINTR.CAUScausatives from other intransitive verbal stems",syn);
	
	// {"-kun(-*kin)", "ADJ.CAUS", "causatives from nouns and adjectives(Yukatek gloss)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","causatives"));f.add(new FeaturePair("subtype","from nouns and adjectives"));
	f.add(new FeaturePair("subtype","Yukatek gloss"));
	ex=new ArrayList<String>();
	ex.add("-kun");ex.add("-*kin");
	expl=new ExplanationPair(ex, "causatives from nouns and adjectives(Yukatek gloss)");
	syn=new SyntacticFeature("ADJ.CAUS",f,expl);
	syn.setStem(true);
	vd.put("-kun(-*kin)ADJ.CAUScausatives from nouns and adjectives(Yukatek gloss)",syn);
	
	// {"-aj", "INTRVZ.INCH", "inchoatives from nouns"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","inchoative"));f.add(new FeaturePair("subtype","from nouns"));
	ex=new ArrayList<String>();
	ex.add("-aj");
	expl=new ExplanationPair(ex, "inchoatives from nouns");
	syn=new SyntacticFeature("INTRVZ.INCH",f,expl);
	syn.setStem(true);
	vd.put("-ajINTRVZ.INCHinchoatives from nouns",syn);
	
	//{"-VVn", "INCH", "inchoatives from nouns, 'becoming' (e.g. ajaw-ni = ajaw-aan)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","inchoative"));f.add(new FeaturePair("subtype","from nouns"));
	f.add(new FeaturePair("subtype","becoming"));
	ex=new ArrayList<String>();
	ex.add("-VVn");
	expl=new ExplanationPair(ex, "inchoatives from nouns, 'becoming' (e.g. ajaw-ni = ajaw-aan)");
	syn=new SyntacticFeature("INCH",f,expl);
	syn.setStem(true);
	vd.put("-VVnINCHinchoatives from nouns, 'becoming' (e.g. ajaw-ni = ajaw-aan)",syn);
	
	// {"-Vj-", "INTRVZ.INCH", "inchoatives from nouns (e.g. pet-j-al)"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","inchoative"));f.add(new FeaturePair("subtype","from nouns"));
	ex=new ArrayList<String>();
	ex.add("-Vj");
	expl=new ExplanationPair(ex, "inchoatives from nouns, (e.g. pet-j-al)");
	syn=new SyntacticFeature("INTRVZ.INCH",f,expl);
	syn.setStem(true);
	vd.put("-Vj-INTRVZ.INCHinchoatives from nouns (e.g. pet-j-al)",syn);
	
	return vd;
}

public Map<String,SyntacticFeature> generateAdverbsLexical() {
	Map<String,SyntacticFeature> al =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	//{"", "ADV", "adverbs"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));
	expl=new ExplanationPair("adverbs");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("ADVadverbs",syn);
	
	// {"", "ADVLZ", "adverbializer"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverbializer"));
	expl=new ExplanationPair("adverbs");
	syn=new SyntacticFeature("ADVLZ",f,expl);
	syn.setStem(true);
	al.put("ADVLZadverbializer",syn);
	
	// {"bay", "ADV", "indeed"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb")); f.add(new FeaturePair("subtype","indeed"));
	ex=new ArrayList<String>();
	ex.add("bay");
	expl=new ExplanationPair(ex,"indeed");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("bayADVindeed",syn);
	
	//{"cha'", "ADV", "again, second time"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb")); f.add(new FeaturePair("subtype","again"));
	f.add(new FeaturePair("subtype","second time"));
	ex=new ArrayList<String>();
	ex.add("cha'");
	expl=new ExplanationPair(ex,"again, second time");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("cha'ADVagain, second time",syn);
	
	//{"ka'", "ADV", "then"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","then"));
	ex=new ArrayList<String>();
	ex.add("ka'");
	expl=new ExplanationPair(ex,"then");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("kaADVthen",syn);
	
	//{"lat", "ADV", "until"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","until"));
	ex=new ArrayList<String>();
	ex.add("lat'");
	expl=new ExplanationPair(ex,"until");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("latADVuntil",syn);
	
	//{"ma' / mach", "ADV", "no, not"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","no"));f.add(new FeaturePair("subtype","not"));
	ex=new ArrayList<String>();
	ex.add("ma''");ex.add("mach'");
	expl=new ExplanationPair(ex,"no, not");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("ma' / machADVno, not",syn);
	
	// {"naach", "ADV", "far"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","far"));
	ex=new ArrayList<String>();
	ex.add("nach'");
	expl=new ExplanationPair(ex,"far");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("nachADVfar",syn);
	
	// {"sa'miiy", "ADV", "earlier today"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","earlier today"));
	ex=new ArrayList<String>();
	ex.add("sa'miiy'");
	expl=new ExplanationPair(ex,"early today");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("sa'miiyADVearlier today",syn);
	
	// {"xa'", "ADV", "already, again, once more"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","already"));f.add(new FeaturePair("subtype","once more"));
	ex=new ArrayList<String>();
	ex.add("xa'");
	expl=new ExplanationPair(ex,"already, again, once more");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("xaADValready, again, once more",syn);
	
	// {"ak'biijy", "ADV", "yesterday"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","yesterday"));
	ex=new ArrayList<String>();
	ex.add("ak'biijy'");
	expl=new ExplanationPair(ex,"yesterday");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("ak'biijyADVyesterday",syn);
	
	// {"o'n", "ADV", "many"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","many"));
	ex=new ArrayList<String>();
	ex.add("o'n'");
	expl=new ExplanationPair(ex,"many");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("o'nADVmany",syn);
	
	//   		 {"uux / oox", "ADV", "abundantly"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","abundantly"));
	ex=new ArrayList<String>();
	ex.add("uux'");ex.add("oox'");
	expl=new ExplanationPair(ex,"abundantly");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("uux / ooxADVabundantly",syn);
	
	//	 {"yuwal", "ADV", "now, then"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","now"));f.add(new FeaturePair("subtype","then"));
	ex=new ArrayList<String>();
	ex.add("yuwal'");
	expl=new ExplanationPair(ex,"now, then");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("yuwalADVnow, then",syn);
	
	//	 {"wal", "ADV", "during"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","during"));
	ex=new ArrayList<String>();
	ex.add("wal'");
	expl=new ExplanationPair(ex,"during");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("walADVduring",syn);
	
	//	{"wa", "ADV", "then"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","then"));
	ex=new ArrayList<String>();
	ex.add("wa'");
	expl=new ExplanationPair(ex,"then");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("waADVthen",syn);
	
	// {"jun pas", "ADV", "next day"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverb"));f.add(new FeaturePair("subtype","next day"));
	ex=new ArrayList<String>();
	ex.add("jun pas'");
	expl=new ExplanationPair(ex,"next day");
	syn=new SyntacticFeature("ADV",f,expl);
	syn.setStem(true);
	al.put("jun pasADVnext day",syn);
	
	return al;
}

public Map<String,SyntacticFeature> generateAdverbsBound() {
	Map<String,SyntacticFeature> ab =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"", "ADVLZ", "adverbializer"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adverbializer"));;f.add(new FeaturePair("subtype","bound"));
	expl=new ExplanationPair("adverbs");
	syn=new SyntacticFeature("ADVLZ",f,expl);
	syn.setStem(true);
	ab.put("ADVLZadverbializer",syn);
	
	return ab;
}

public Map<String,SyntacticFeature> generateAdjectiveLexical() {
	Map<String,SyntacticFeature> ab =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"", "ADJ", "adjectives"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjective"));;f.add(new FeaturePair("subtype","lexical"));
	expl=new ExplanationPair("adjectives");
	syn=new SyntacticFeature("ADJ",f,expl);
	syn.setStem(true);
	ab.put("ADJadjectives",syn);
	
	return ab;
}

public Map<String,SyntacticFeature> generateNumeralLexical() {
	Map<String,SyntacticFeature> nl =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"", "NUM", "numerals"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","numeral"));;f.add(new FeaturePair("subtype","lexical"));
	expl=new ExplanationPair("numerals");
	syn=new SyntacticFeature("NUM",f,expl);
	syn.setStem(true);
	nl.put("NUMnumerals",syn);
	
	return nl;
}

public Map<String,SyntacticFeature> generateNounLexical() {
	Map<String,SyntacticFeature> nol =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// {"", "ADJ", "adjectives"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","noun"));f.add(new FeaturePair("subtype","lexical"));
	expl=new ExplanationPair("noun (lexical)");
	syn=new SyntacticFeature("N",f,expl);
	syn.setStem(true);
	nol.put("Nnoun (lexical)",syn);
	
	return nol;
}

public Map<String,SyntacticFeature> generateVerbLexical() {
	Map<String,SyntacticFeature> v =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// 	   		 {"", "V", "verb (lexical)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","verb"));f.add(new FeaturePair("subtype","lexical"));
	expl=new ExplanationPair("noun (lexical)");
	syn=new SyntacticFeature("N",f,expl);
	syn.setStem(true);
	v.put("Vverb (lexical)",syn);
	
	return v;
}
public Map<String,SyntacticFeature> generateAdjectivalDerivation() {
	Map<String,SyntacticFeature> ad =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	// {"", "PTCP", "participle"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","participle"));
	expl=new ExplanationPair("participle");
	syn=new SyntacticFeature("PTCP",f,expl);
	syn.setStem(true);
	ad.put("PTCPparticiple",syn);
	
	// {"", "ADJVZ", "adjectivizer"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjectivizer"));
	expl=new ExplanationPair("adjectivizer");
	syn=new SyntacticFeature("ADJVZ",f,expl);
	syn.setStem(true);
	ad.put("ADJVZadjectivizer",syn);
	
	// {"", "INTENS", "intensifier"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intensifier"));
	expl=new ExplanationPair("intensifier");
	syn=new SyntacticFeature("INTENS",f,expl);
	syn.setStem(true);
	ad.put("INTENSintensifier",syn);
	
	//{"-Vl", "ADJ", "adjectives from nouns (k'ahk'-al, k'uh-ul)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjective"));f.add(new FeaturePair("subtype","from nouns"));
	ex=new ArrayList<String>();
	ex.add("-Vl'");
	expl=new ExplanationPair(ex,"adjectives from nouns (k'ahk'-al, k'uh-ul)");
	syn=new SyntacticFeature("ADJ",f,expl);
	syn.setStem(true);
	ad.put("-VlADJadjectives from nouns (k'ahk'-al, k'uh-ul)",syn);
	
	// {"-Vch", "ADJ", "adjectives from nouns (k'ihnich)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjective"));f.add(new FeaturePair("subtype","from nouns"));
	ex=new ArrayList<String>();
	ex.add("-Vch'");
	expl=new ExplanationPair(ex,"adjectives from nouns (k'ihnich)");
	syn=new SyntacticFeature("ADJ",f,expl);
	syn.setStem(true);
	ad.put("-VchADJadjectives from nouns (k'ihnich)",syn);
	
	//{"-Vm", "ADJ", "adjectives from nouns (chil-iim)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjective"));f.add(new FeaturePair("subtype","from nouns"));
	ex=new ArrayList<String>();
	ex.add("-Vm'");
	expl=new ExplanationPair(ex,"adjectives from nouns (chil-iim)");
	syn=new SyntacticFeature("ADJ",f,expl);
	syn.setStem(true);
	ad.put("-VmADJadjectives from nouns (chil-iim)",syn);
	
	// {"-VVl", "STAT.PTCP", "stative participles from transitive and positional verbal stems"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","participles"));f.add(new FeaturePair("subtype","stative"));f.add(new FeaturePair("subtype","from transitive and positional verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-VVl'");
	expl=new ExplanationPair(ex,"stative participles from transitive and positional verbal stems");
	syn=new SyntacticFeature("STAT.PCP",f,expl);
	syn.setStem(true);
	ad.put("-VVlSTAT.PTCPstative participles from transitive and positional verbal stems",syn);
	
	//	 {"-bil", "PRF.TR.PTCP", "perfect participles from transitive verbal stems (k'ahk'-bil)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","participles"));f.add(new FeaturePair("subtype","perfect"));f.add(new FeaturePair("subtype","from transitive verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-bil'");
	expl=new ExplanationPair(ex,"perfect participles from transitive verbal stems");
	syn=new SyntacticFeature("PRF.TR.PTCP",f,expl);
	syn.setStem(true);
	ad.put("-bilPRF.TR.PTCPperfect participles from transitive verbal stems (k'ahk'-bil)",syn);
	
	//	 {"-em", "PRF.PTCP", "perfect participles from verbal stems (e.g. tzutz-em)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","participles"));f.add(new FeaturePair("subtype","perfect"));f.add(new FeaturePair("subtype","from verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-em'");
	expl=new ExplanationPair(ex,"perfect participles from verbal stems");
	syn=new SyntacticFeature("PRF.TR.PTCP",f,expl);
	syn.setStem(true);
	ad.put("-emPRF.PTCPperfect participles from verbal stems (e.g. tzutz-em)",syn);
	
	//	 {"-om", "FUT.PTCP", "perfect participles from verbal stems (e.g. tzutz-om)"}, 
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","participles"));f.add(new FeaturePair("subtype","perfect"));f.add(new FeaturePair("subtype","from verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-om'");
	expl=new ExplanationPair(ex,"perfect participles from verbal stems (e.g. tzutz-om)");
	syn=new SyntacticFeature("FUT.PTCP",f,expl);
	syn.setStem(true);
	ad.put("-omFUT.PTCPperfect participles from verbal stems (e.g. tzutz-om)",syn);
	
	//	 {"-VVl", "PRF.PTCP", "perfect participles from verbal stems (e.g. joch'-ool)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","participles"));f.add(new FeaturePair("subtype","perfect"));f.add(new FeaturePair("subtype","from verbal stems"));
	ex=new ArrayList<String>();
	ex.add("-VVl'");
	expl=new ExplanationPair(ex,"perfect participles from verbal stems (e.g. joch'-ool)");
	syn=new SyntacticFeature("PRF.PTCP",f,expl);
	syn.setStem(true);
	ad.put("-VVlPRF.PTCPperfect participles from verbal stems (e.g. joch'-ool)",syn);
	
	// {"-il", "ADJ", "adjectives from compound nouns"},	
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjectives"));f.add(new FeaturePair("subtype","from compound nouns"));
	ex=new ArrayList<String>();
	ex.add("-il'");
	expl=new ExplanationPair(ex,"adjectives from compound nouns");
	syn=new SyntacticFeature("ADJ",f,expl);
	syn.setStem(true);
	ad.put("-ilADJadjectives from compound nouns",syn);
	
	// 		 {"-Vl", "ADJVZ", "adjectivizer (e.g. pet-j-al)"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjectivizer"));
	ex=new ArrayList<String>();
	ex.add("-Vl'");
	expl=new ExplanationPair(ex,"adjectivizer (e.g. pet-j-al)");
	syn=new SyntacticFeature("ADJVZ",f,expl);
	syn.setStem(true);
	ad.put("-VlADJVZadjectivizer (e.g. pet-j-al)",syn);
	
	// {"", "INTENS", "intensifier of adjectives (e.g. ya-yax)"} 
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","intensifier"));f.add(new FeaturePair("subtype","of adjectives"));
	expl=new ExplanationPair("intensifier of adjectives (e.g. ya-yax)");
	syn=new SyntacticFeature("INTENS",f,expl);
	syn.setStem(true);
	ad.put("INTENSintensifier of adjectives (e.g. ya-yax)",syn);
	
	return ad;
}

public Map<String,SyntacticFeature> generatePrepositions() {
	Map<String,SyntacticFeature> prep =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// 	 {"", "PREP", "adjectives"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","adjectives"));
	expl=new ExplanationPair("adjectives");
	syn=new SyntacticFeature("PREP",f,expl);
	syn.setStem(true);
	prep.put("PREPadjectives",syn);
	
	//	 {"ti", "PREP", "to, with, on, in, for"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("semantics","to"));	f.add(new FeaturePair("semantics","with"));	f.add(new FeaturePair("semantics","on"));	f.add(new FeaturePair("semantics","for"));
	ex=new ArrayList<String>();
	ex.add("ti");
	expl=new ExplanationPair(ex,"to, with, on, in, for");
	syn=new SyntacticFeature("PREP",f,expl);
	syn.setStem(true);
	prep.put("tiPREPto, with, on, in, for",syn);
	 // {"ta", "PREP", "to, with, on, in, for"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("semantics","to"));	f.add(new FeaturePair("semantics","with"));	f.add(new FeaturePair("semantics","on"));	f.add(new FeaturePair("semantics","for"));
	ex=new ArrayList<String>();
	ex.add("ta");
	expl=new ExplanationPair(ex,"to, with, on, in, for");
	syn=new SyntacticFeature("PREP",f,expl);
	syn.setStem(true);
	prep.put("taPREPto, with, on, in, for",syn);
	
	//{"tahn", "PREP", "in the middle	
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("semantics","in the middle"));	
	ex=new ArrayList<String>();
	ex.add("tahn");
	expl=new ExplanationPair(ex,"in the middle");
	syn=new SyntacticFeature("PREP",f,expl);
	syn.setStem(true);
	prep.put("tahnPREPin the middle",syn);
	
	//	 {"ichil", "PREP", "in, inside"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("semantics","in"));		f.add(new FeaturePair("semantics","inside"));	
	ex=new ArrayList<String>();
	ex.add("ichil");
	expl=new ExplanationPair(ex,"in, inside");
	syn=new SyntacticFeature("PREP",f,expl);
	syn.setStem(true);
	prep.put("ichilPREPin, inside",syn);
	
	return prep;
}

public Map<String,SyntacticFeature> generateParticles() {
	Map<String,SyntacticFeature> par =new HashMap<String, SyntacticFeature>();
	ArrayList<FeaturePair> f=new ArrayList<FeaturePair>();
	ExplanationPair expl;
	ArrayList<String> ex=new ArrayList<String>();
	SyntacticFeature syn;
	
	// 	 {"", "PART", "particles"},
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("type","particles"));
	expl=new ExplanationPair("particles");
	syn=new SyntacticFeature("PART",f,expl);
	syn.setStem(true);
	par.put("PARTparticles",syn);
	
	//	 	 {"che", "PART", "thus"}
	f=new ArrayList<FeaturePair>();
	f.add(new FeaturePair("semantics","thus"));
	ex=new ArrayList<String>();
	ex.add("che");
	expl=new ExplanationPair(ex,"thus");
	syn=new SyntacticFeature("PART",f,expl);
	syn.setStem(true);
	par.put("chePARTthus",syn);
	
	return par;
}
}

