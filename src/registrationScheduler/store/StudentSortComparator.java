package registrationScheduler.store;


import java.util.Comparator;
/**
 * 
 * @author samara
 *
 */
public class StudentSortComparator implements Comparator<String>{

	@Override
	public int compare(String student1_String, String student2_String) {
		try {
			String[] st1= student1_String.split(InterfaceConstants.UNDER_SCORE);
			String[] st2= student2_String.split(InterfaceConstants.UNDER_SCORE);
			Integer s1=Integer.parseInt(st1[1]);	
			Integer s2=Integer.parseInt(st2[1]);
			return s1.compareTo(s2);
		} catch (NumberFormatException e) {
			System.err.println("NumberForamtException in "+StudentSortComparator.class.getName());
			System.exit(1);
		} finally{

		}
		return 0;
	}

}
