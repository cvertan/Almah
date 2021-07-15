package allmahVer4;

public interface GlossingValues {
	public String[] Pronominals={"Set A (Absolutive Case","Set B (Ergative Case", "Set C (Independent Emphatic Pronouns"};


	 public Object[][] ABSOLUTIVE_CASE= {
			 {"-ø", "3s.ABS", "3rd person, singular and plural"},
			   		 {"-een (-Ce-na)", "1s.ABS", "1st person, singular"},
			   		 {"-at? (-Ce-ta)", "2s.ABS", "2nd person, singular"},
			   		 {"-oon (-Co-na)", "1p.ABS", "1st person, plural"},
			   		 {"-oob (-Co-ba)", "3p.ABS", "3rd person, plural"},
					 {"", "2p.ABS", "2nd person, plural","2","plural"}
			    };

			    public Object[][] ERGATIVE_CASE={
			   		 {"u-", "3s.ERG", "3rd person, singular, consonant initial stem"},
			   		 {"ni-", "1s.ERG", "1st person, singular, consoant initial stem"},
			   		 {"a-", "2s.ERG", "2nd person, singular, consonant initial stem"},
			   		 {"ka-", "1p.ERG", "1st person, plural, consonant initial stem"},
					 {"", "3p.ERG", "3rd person, plural, consonant initial stem"},
					 {"", "2p.ERG", "2nd person, plural, consonant initial stem"},
			   		 {"y-", "3s.ERG", "3rd person, singular, vowel initial stem"},
			   		 {"w-", "1s.ERG", "1st person, singular, vowel initial stem"},
			   		 {"aw- (a-wV-)", "2s.ERG", "2nd person, singular, vowel initial stem"},
			 {"", "2p.ERG", "2nd person, plural, vowel initial stem"} 
			    };
			    
			    public Object[][] INDEPENDENT_OR_DEMONSTRATIVE_PRONOUNS= {
			   	                                                                                                                                                                                                                                                                                                       
			};

			    public Object[][] NOUN_LEXICAL= {
			   		 {"", "N", "noun (lexical)"},    
			};

			    public Object[][] NOUN_INFLECTION={
			   		 {"", "RELZ", "relationalizer (inalienable possession)"},
			   		 {"", "ABS", "absoluble, unpossessed form of body part"},   	 
			   		 {"-il", "RELZ", "relationalizer (inalienable possession, u baah-il aan)"},
			   		 {"-el", "RELZ", "relationalizer (inalienable possession of body, substances, blood, bones, skin and hair (e.g. u bak-el)"},
			   		 {"-is", "ABS", "absoluble, unpossessed form of body part (e.g. ohl-is)"}
			   		 
			};

			    public Object[][] NOUN_DERIVATION= {
			   		 {"", "ABSTR", "abstract noun",},
			   		 {"", "NMLZ", "nominalizer"},
			   		 {"", "LOC", "locative noun"},
			   		 {"", "AGT", "agentive noun"},
			   		 {"", "INSTR", "instrumental noun"},
			   		 {"", "AGT", "agentive"},
			   		 {"", "F", "female proclitic"},
			   		 {"", "PL", "collective nouns, plural"},           
			   		 {"", "VN", "verbal noun"},
			   		 {"-il", "ABSTR", "abstract noun (e.g. ajaw-il)"},
			   		 {"-lel", "ABSTR", "abstract noun (e.g. ajaw-lel)"},
			   		 {"-lil", "ABSTR", "abstract noun (e.g. ajaw-lil)"},
			   		 {"-Vl", "ABSTR", "abstract noun (e.g. te'-el)"},
			   		 {"-aj", "NMLZ", "nominalizer (e.g. tek'-aj, pas-aj)"},
			   		 {"-il", "NMLZ", "nominalizer (compound verbs, e.g. u k'altuun-il)"},
			   		 {"-el", "NMLZ", "nominalizer, gerundival nouns (e.g. y-och-el, u joy-el)"},
			   		 {"-an", "NMLZ", "nominalizer (few nouns only)"},
					 {"-Vn", "NMLZ", "nominalizer (e.g. pet-en)"},
					 {"-Vt", "NMLZ", "nominalizer (e.g. muk'-ut)"},
			   		 {"-Vl", "LOC", "place-name from noun, local case relator (where X abounds)"},
			   		 {"-nal", "LOC", "locative suffix"},
			   		 {"-aal", "ABSTR", "noun from noun (e.g. tz'ihb-aal)"},
			   		 {"-taak (-ta-ki)", "PL", "collective noun (e.g. ajaw-taak)"},
			   		 {"-a", "AGT", "suffix of agentive noun (from verbs and nouns, hul-a)"},
			   		 {"aj-", "AGT", "prefix of agentive noun (from verb and nouns)"},
			   		 {"ix-", "F", "female proclitic (from verb and nouns)"},
			   		 {"-oom", "AGT", "suffix of agentive noun (from verbs)"},
			   		 {"-ib'", "NMLZ.INSTR", "instrumental noun from verb (instruments and places)"},
			   		 {"-Vl", "NMLZ.VN", "nominalizer of verb (e.g. chok-ol"},
			   		 {"-Vl", "NMLZ.VN", "derives noun with instrumental meanings from verb (pok-ol)"},
			   		 {"-VVb (-Ca-bi, Cu-bi)", "NMLZ.INSTR", "suffix of agentive noun (from verbs)"},
			   		 {"+", "+", "composite noun, compound nouns and verbs (k'intuun, elk'in)"}
			};

			public Object[][] NUMERALS_LEXICAL= {
			   		 {"", "NUM", "numeral (lexical)"},
			};


			    public Object[][] NUMERALS_BOUND= {
			   		 {"", "CLF", "classifier"},
			   		 {"-ij (-ji)", "ADVLZ.FUT", "adverbializer, counting of time periods (in N days)"},  
			   		 {"-ij-iiy (-ji-ya)", "ADVLZ.ANT", "adverbializer, counting of time periods from the past (N days later)"},
			   		 {"-lat, -he'w, -tal", "CLF", "count of days"},
			   		 {"-he'n", "CLF", "count of days"},
			   		 {"-bix", "CLF", "count of either 5 or 7 days"},
			   		 {"-te'", "CLF", "default numeral classifier"},
			   		 {"-tz'ak","CLF", "count of successors"},
			   		 {"-pis", "CLF", "count of years"},
			   		 {"-kob", "CLF", "count of twins"},
			   		 {"-kul", "CLF", "count of stones and years"},
			   		 {"-mul", "CLF", "count of stacked objects"},
			   		 {"-nak", "CLF", "count of lower titles"},
			   		 {"-pet", "CLF", "count of circular objects"},
			   		 {"-tikil", "CLF", "count of people"},
			   		 {"-tuk", "CLF", "count of stacks"},
			   		 {"-nahb", "CLF", "count of handspans"}
			   		 
			   	 
			};

			public Object[][] VERBS_LEXICAL= {
			   		 {"", "V", "verb (lexical)"},
			   		
			};

			    public Object[][] THEMATIC_SUFFIXES= {
			   		 {"-i", "THM", "thematic suffix for intransitive verbs"},
			   		 {"-Vw", "TR", "transitive verbs"},
			   		 {"-VVn", "TR.D", "derived transitive verbs"},
			   		 {"-i", "INTR", "intransitive verbs"},
			   		 {"-el", "INTR", "intransitive verbs"},
			   		 {"-aj", "INTR.D", "derived intransitive verbs (e.g. ahk't-aj, joy-aj, tup-aj, k'alhun-aj)"},
			   		 {"-iij", "INTR.D.", "derived intransitive verbs (e.g. witz-iij, tak-iij"},
			   		 {"-laj", "POS", "positional verbs (e.g. chum-laj"},
			   		 {"-waan", "POS", "positional verbs (e.g. chum-waan)"},
			   		 {"-laj", "AFF", "affective verbs (e.g. bah-laj, yuhk-laj)"},
			   		 {"-l-", "POS", "positional verbs (e.g. chum-l-iiy)"},
			   		 {"-VVj", "PRF", "perfect"},
			   		 {"-Vk", "MOD.OPT.V.INTR", "optative"},
			   		 {"-V", "IMP", "imperatives from CVC transitive verbal stems"},
			   		 {"-oom", "FUT", "unavoidable future (prophetic future)"}
			    };
			    
			    public Object[][] VERBAL_CLITICS= {
			   		 {"-iiy (Vi-ya)", "ANT", "anterior, deictic clitic, mostly referring to events in the past"},
			   		 {"i-", "THEN", "then"},
			   		 {"wa-", "PROG", "progressive/durative aspect"},
			   		 {"xa-", "ALREADY", "already"},
			   		 {"ma'", "NEG", "negative particle"},
					 {"", "CLT", "general clitic"}
			    };
			    
			    public Object[][] VALENCY_DECREASING_INCREASING_SUFFIXES= {
			   		 {"", "PAS", "passive"},
			   		 {"", "MED", "mediopassive, middle voice"},
			   		 {"", "AP", "antipassive"},
			   		 {"-h-", "PAS", "infix of passives fromCVC transitiv verbal stems"},
			   		 {"-n-", "PAS.D", "suffix of passives from derived transitive verbal stems"},
			   		 {"-w-", "PAS.D", "suffix of passive from derived transitive verbal stems (dialectical variant)"},
			   		 {"-Vb-", "PAS.D", "passive (Yucatecan)"},
			   		 {"-VVy", "MED", "mediopassive; middle voice (state of change verbs)"},
			   		 {"-ey", "MED", "mediopassive; middle voice (state of change verbs) of glottal stop initial verb stems"},
			   		 {"-Vw", "AP", "antipassives from CVC transitive verbal stems"},
			   		 {"-(o)n, -n-", "AP", "antipassives from non-CVC transitive verbal stems (e.g. ahk'-n-om) "},
			   		 {"-ø", "AP.D", "antipassives from derived transitive verbal stems"}
			    };
			    
			    public Object[][] VERBAL_DERIVATION= {
			   		 {"", "CAUS", "causative"},
			   		 {"", "INCH", "inchoative"},
			   		 {"-b'u(-*b'a)", "POS.CAUS", "causatives from positional verbal stems"},
			   		 {"-es", "INTR.CAUS", "causatives from other intransitive verbal stems"},
			   		 {"-kun(-*kin)", "ADJ.CAUS", "causatives from nouns and adjectives(Yukatek gloss)"},
			   		 {"-aj", "INTRVZ.INCH", "inchoatives from nouns"},
			   		 {"-VVn", "INCH", "inchoatives from nouns, 'becoming' (e.g. ajaw-ni = ajaw-aan)"},
			   		 {"-Vj-", "INTRVZ.INCH", "inchoatives from nouns (e.g. pet-j-al)"}
			    };
			   
			    public Object[][] ADVERBS_LEXICAL= {
			   		 {"", "ADV", "adverbs"},
			   		 {"", "ADVLZ", "adverbializer"},
			   		 {"bay", "ADV", "indeed"},
			   		 {"cha'", "ADV", "again, second time"},
			   		 {"ka'", "ADV", "then"},
			   		 {"lat", "ADV", "until"},
			   		 {"ma' / mach", "ADV", "no, not"},
			   		 {"naach", "ADV", "far"},
			   		 {"sa'miiy", "ADV", "earlier today"},
			   		 {"xa'", "ADV", "already, again, once more"},
			   		 {"ak'biijy", "ADV", "yesterday"},
			   		 {"o'n", "ADV", "many"},
			   		 {"uux / oox", "ADV", "abundantly"},
			   		 {"yuwal", "ADV", "now, then"},
			   		 {"wal", "ADV", "during"},
					{"wa", "ADV", "then"},
			   		 {"jun pas", "ADV", "next day"}
			    };

			    public Object[][] ADVERBS_BOUND= {
			   		 {"", "ADVLZ", "adverbializer"},
			   		 
			    };

			    public Object[][] ADJECTIVES_LEXICAL= {
			   		 {"", "ADJ", "adjectives"}
			    };
			    
			    public Object[][] ADJECTIVAL_DERIVATION= {
			   		 {"", "PTCP", "participle"},
			   		 {"", "ADJVZ", "adjectivizer"},
			   		 {"", "INTENS", "intensifier"},
			   		 {"-Vl", "ADJ", "adjectives from nouns (k'ahk'-al, k'uh-ul)"},
			   		 {"-Vch", "ADJ", "adjectives from nouns (k'ihnich)"},
			   		 {"-Vm", "ADJ", "adjectives from nouns (chil-iim)"},
			   		 {"-VVl", "STAT.PTCP", "stative participles from transitive and positional verbal stems"},
			   		 {"-bil", "PRF.TR.PTCP", "perfect participles from transitive verbal stems (k'ahk'-bil)"},
			   		 {"-em", "PRF.PTCP", "perfect participles from verbal stems (e.g. tzutz-em)"},
			   		 {"-om", "FUT.PTCP", "perfect participles from verbal stems (e.g. tzutz-om)"},    
			   		 {"-VVl", "PRF.PTCP", "perfect participles from verbal stems (e.g. joch'-ool)"},
			   		 {"-il", "ADJ", "adjectives from compound nouns"},
			   		 {"-Vl", "ADJVZ", "adjectivizer (e.g. pet-j-al)"},
			   		 {"", "INTENS", "intensifier of adjectives (e.g. ya-yax)"}
			    };
			    
			    public Object[][] PREPOSITIONS= {
			   		 {"", "PREP", "adjectives"},
			   		 {"ti", "PREP", "to, with, on, in, for"},
			   		 {"ta", "PREP", "to, with, on, in, for"},
			   		 {"tahn", "PREP", "in the middle"},
			   		 {"ichil", "PREP", "in, inside"}
			   		 
			    
			    };

			    public Object[][] PARTICLES= {
			   		 {"", "PART", "particles"},
			   		 {"che", "PART", "thus"}
			    
			    };

    
   
}
