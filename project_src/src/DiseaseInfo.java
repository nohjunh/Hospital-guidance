public class DiseaseInfo {
	public static String GetDiseaseName(int diseaseNum) {
		switch(diseaseNum) {
		case 1: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>�㳶��, �����, ����,<br>����� �Դϴ�.</body></html>";
	    case 2: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>�޼������, ����, ���˾�,<br> ��������˾� �Դϴ�.</body></html>";
	    case 3: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>���˾�, �޼����忰 �Դϴ�.</body></html>";
	    case 4: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>����Ἦ, Ż��,<br>�ſ�ſ� �Դϴ�.</body></html>";
	    case 5: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>�ɱٰ��, �޼����忰,<br>����� �Դϴ�.</body></html>";
	    case 6: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>����Ἦ, �޼����忰,<br>�ſ�ſ� �Դϴ�.</body></html>";
	    case 7: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>�޼������, ������ȯ �Դϴ�.</body></html>";
	    case 8: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>��ݿ�, �ڱ� �� ������ȯ,<br>�汤�� �Դϴ�.</body></html>";
	    case 9: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��<br>������ȯ, �䵵��ȯ �Դϴ�.</body></html>";
	    default: return "<html><body style='text-align:center;'>����Ǵ� ��ȯ��?</body></html>";
		}
	}
	
	public static String GetDiseaseSubject(int diseaseNum) {
		switch(diseaseNum) {
		case 1: case 2: case 3: case 4: case 5: return "����";
	    case 6: return "�ܰ�";
	    case 7: return "����ΰ�";
	    case 8: case 9: return "�񴢱��";
	    default: return "����";
		}
	}
}
