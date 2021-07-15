package allmahVer4;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class LinguisticTables {
	protected String[] colPoSValues={"Type","PoS","Explanation"};
	protected JTable selectedTable, absolutiveCase, ergativeCase, indepEmphPronouns;
	protected JTable verbLexical, thematicSuffixes,valencySuffixes, verbalDerivation;
	protected JTable adjectiveLexical, adjectivalDerivation;
	protected JTable adverbLexical, adverbBound;
	protected JTable nounLexical, nounInflection, nounDerivation;
	protected JTable numeralLexical, numeralBound;
	protected JTable  verbalClitics, otherParticle, preposition;
	public LinguisticTables() {
		 absolutiveCase = new JTable(GlossingValues.ABSOLUTIVE_CASE, colPoSValues);
		   absolutiveCase.setFillsViewportHeight(true);
		   absolutiveCase.setAutoCreateRowSorter(true);
		   absolutiveCase.setCellSelectionEnabled(true);
		    absolutiveCase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   ergativeCase=new JTable(GlossingValues.ERGATIVE_CASE, colPoSValues);
		   ergativeCase.setFillsViewportHeight(true);
		   ergativeCase.setAutoCreateRowSorter(true);
		   ergativeCase.setCellSelectionEnabled(true);
		     ergativeCase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   indepEmphPronouns=new JTable(GlossingValues.INDEPENDENT_OR_DEMONSTRATIVE_PRONOUNS, colPoSValues);
		   indepEmphPronouns.setFillsViewportHeight(true);
		  indepEmphPronouns.setAutoCreateRowSorter(true);
		 indepEmphPronouns.setCellSelectionEnabled(true);
	      indepEmphPronouns.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      /*
	       
	private JTable adjectiveLexical, adjectivalDerivation;
	private JTable adverbLexical, adverbDerivation;
	private JTable nounLexical, nounInflection, nounDerivation;
	private JTable numeralLexical, numeralBound;
	private JTable  verbalClitics, otherParticle, preposition;

	       */
	      verbLexical=new JTable(GlossingValues.VERBS_LEXICAL, colPoSValues);
		  verbLexical.setFillsViewportHeight(true);
		   verbLexical.setAutoCreateRowSorter(true);
		   verbLexical.setCellSelectionEnabled(true);
		    verbLexical.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   thematicSuffixes=new JTable(GlossingValues.THEMATIC_SUFFIXES, colPoSValues);
		   thematicSuffixes.setFillsViewportHeight(true);
		   thematicSuffixes.setAutoCreateRowSorter(true);
		   thematicSuffixes.setCellSelectionEnabled(true);
		      thematicSuffixes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   verbalClitics=new JTable(GlossingValues.VERBAL_CLITICS, colPoSValues);
		   verbalClitics.setFillsViewportHeight(true);
		  verbalClitics.setAutoCreateRowSorter(true);
		  verbalClitics.setCellSelectionEnabled(true);
	      verbalClitics.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      otherParticle=new JTable(GlossingValues.PARTICLES, colPoSValues);
		   otherParticle.setFillsViewportHeight(true);
		otherParticle.setAutoCreateRowSorter(true);
		  otherParticle.setCellSelectionEnabled(true);
	     otherParticle.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	     preposition=new JTable(GlossingValues.PREPOSITIONS, colPoSValues);
		   preposition.setFillsViewportHeight(true);
		 preposition.setAutoCreateRowSorter(true);
		  preposition.setCellSelectionEnabled(true);
	     preposition.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   valencySuffixes=new JTable(GlossingValues.VALENCY_DECREASING_INCREASING_SUFFIXES, colPoSValues);
		   valencySuffixes.setFillsViewportHeight(true);
		   valencySuffixes.setAutoCreateRowSorter(true);
		  valencySuffixes.setCellSelectionEnabled(true);
		     valencySuffixes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   verbalDerivation=new JTable(GlossingValues.VERBAL_DERIVATION, colPoSValues);
		   verbalDerivation.setFillsViewportHeight(true);
		   verbalDerivation.setAutoCreateRowSorter(true);
		   verbalDerivation.setCellSelectionEnabled(true);
		    verbalDerivation.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		    adjectiveLexical=new JTable(GlossingValues.ADJECTIVES_LEXICAL, colPoSValues);
			   adjectiveLexical.setFillsViewportHeight(true);
			  adjectiveLexical.setAutoCreateRowSorter(true);
			 adjectiveLexical.setCellSelectionEnabled(true);
		      adjectiveLexical.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   adjectivalDerivation=new JTable(GlossingValues.ADJECTIVAL_DERIVATION, colPoSValues);
		   adjectivalDerivation.setFillsViewportHeight(true);
		  adjectivalDerivation.setAutoCreateRowSorter(true);
		 adjectivalDerivation.setCellSelectionEnabled(true);
	      adjectivalDerivation.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      //
	      adverbLexical=new JTable(GlossingValues.ADVERBS_LEXICAL, colPoSValues);
		   adverbLexical.setFillsViewportHeight(true);
		  adverbLexical.setAutoCreateRowSorter(true);
		 adverbLexical.setCellSelectionEnabled(true);
	      adverbLexical.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	   adverbBound=new JTable(GlossingValues.ADVERBS_BOUND, colPoSValues);
	   adverbBound.setFillsViewportHeight(true);
	   adverbBound.setAutoCreateRowSorter(true);
	   adverbBound.setCellSelectionEnabled(true);
	   adverbBound.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	      //
		   numeralBound=new JTable(GlossingValues.NUMERALS_BOUND, colPoSValues);
		  numeralBound.setFillsViewportHeight(true);
		 numeralBound.setAutoCreateRowSorter(true);
		numeralBound.setCellSelectionEnabled(true);
	     numeralBound.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	     numeralLexical=new JTable(GlossingValues.NUMERALS_LEXICAL, colPoSValues);
		  numeralLexical.setFillsViewportHeight(true);
		 numeralLexical.setAutoCreateRowSorter(true);
		numeralLexical.setCellSelectionEnabled(true);
	     numeralLexical.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	     nounLexical=new JTable(GlossingValues.NOUN_LEXICAL, colPoSValues);
		   nounLexical.setFillsViewportHeight(true);
		  nounLexical.setAutoCreateRowSorter(true);
		 nounLexical.setCellSelectionEnabled(true);
	    nounLexical.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   nounInflection=new JTable(GlossingValues.NOUN_INFLECTION, colPoSValues);
		   nounInflection.setFillsViewportHeight(true);
		  nounInflection.setAutoCreateRowSorter(true);
		 nounInflection.setCellSelectionEnabled(true);
	    nounInflection.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   nounDerivation=new JTable(GlossingValues.NOUN_DERIVATION, colPoSValues);
		   nounDerivation.setFillsViewportHeight(true);
		   nounDerivation.setAutoCreateRowSorter(true);
		  nounDerivation.setCellSelectionEnabled(true);
		      nounDerivation.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

}
