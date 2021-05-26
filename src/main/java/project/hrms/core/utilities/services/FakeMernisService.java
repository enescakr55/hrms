package project.hrms.core.utilities.services;

public class FakeMernisService {
	public static boolean isValid(String ad,String soyad,String tc,String DogumYili) {
		if(tc.length() != 11) {
			return false;
		}
		return true;
	}
}
