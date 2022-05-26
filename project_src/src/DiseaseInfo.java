public class DiseaseInfo {
	public static String GetDiseaseName(int diseaseNum) {
		switch(diseaseNum) {
		case 1: return "<html><body style='text-align:center;'>예상되는 질환은<br>담낭염, 담관염, 간염,<br>간비대 입니다.</body></html>";
	    case 2: return "<html><body style='text-align:center;'>예상되는 질환은<br>급성충수염, 위염, 위궤양,<br> 십이지장궤양 입니다.</body></html>";
	    case 3: return "<html><body style='text-align:center;'>예상되는 질환은<br>위궤양, 급성췌장염 입니다.</body></html>";
	    case 4: return "<html><body style='text-align:center;'>예상되는 질환은<br>신장결석, 탈장,<br>신우신염 입니다.</body></html>";
	    case 5: return "<html><body style='text-align:center;'>예상되는 질환은<br>심근경색, 급성췌장염,<br>충수염 입니다.</body></html>";
	    case 6: return "<html><body style='text-align:center;'>예상되는 질환은<br>신장결석, 급성췌장염,<br>신우신염 입니다.</body></html>";
	    case 7: return "<html><body style='text-align:center;'>예상되는 질환은<br>급성충수염, 난소질환 입니다.</body></html>";
	    case 8: return "<html><body style='text-align:center;'>예상되는 질환은<br>골반염, 자궁 및 난소질환,<br>방광염 입니다.</body></html>";
	    case 9: return "<html><body style='text-align:center;'>예상되는 질환은<br>난소질환, 요도질환 입니다.</body></html>";
	    default: return "<html><body style='text-align:center;'>예상되는 질환은?</body></html>";
		}
	}
	
	public static String GetDiseaseSubject(int diseaseNum) {
		switch(diseaseNum) {
		case 1: case 2: case 3: case 4: case 5: return "내과";
	    case 6: return "외과";
	    case 7: return "산부인과";
	    case 8: case 9: return "비뇨기과";
	    default: return "병원";
		}
	}
}
