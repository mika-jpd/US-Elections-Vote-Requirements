import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {

	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided){
		
		int[] votes_needed  = new int[num_states];

		//CHECKS MINIMUM NUMBER OF VOTES NEEDED TO WIN PER STATE, -1 IF CAN'T WIN
		for (int i = 0; i < num_states; i++) {
			int x = votes_Biden[i] - votes_Trump[i];

			if (x > 0) {//Biden leading
				if (x > votes_Undecided[i]) {
					votes_needed[i] = 0;
				} else {
					int left = votes_Undecided[i] - x;
					votes_needed[i] = (left/2) + 1;
				}

			} else if (x < 0) {//Biden falling behind
				if (votes_Undecided[i] <= Math.abs(x)) votes_needed[i] = -1;
				else {
					int needed = Math.abs(x); 
					int left = votes_Undecided[i] + x;
					needed += ((left)/2) + 1;

					votes_needed[i] = needed;
				}

			} else { // x = 0 the people can't decide
				if (votes_Undecided[i] == 0) votes_needed[i] = -1;
				else {
					votes_needed[i] = (votes_Undecided[i]/2) + 1;
				}
			}
		}

		//CHECKS WHETHER BIDEN CAN WIN ELECTORAL COLLEGE
		int states_Trump = 0;
		int states_Biden = 0;
		int states_need = 0;
		int swing_votes = 0;
		int potential_states = 0;

		for (int i = 0; i < votes_needed.length; i++) {
			if (votes_needed[i] == -1) states_Trump += delegates[i];
			else if (votes_needed[i] == 0) {
				states_Biden += delegates[i];
			}
			else { 
				states_need += delegates[i];
				swing_votes+= delegates[i];
				++potential_states;
			}
		}

		int [] potential_undecided = new int[potential_states];
		int [] potential_delegates = new int[potential_states];
		int k = 0;
		for (int i = 0; i < num_states; i++) {
			if (votes_needed[i] > 0) {
				potential_undecided[k] = votes_needed[i];
				potential_delegates[k] = delegates[i];
				k++;
			}
		}

		if (states_Trump > states_Biden) {
			states_need = (states_Trump - states_Biden) + (((states_need-(states_Trump - states_Biden))/2) + 1);
		} else if (states_Biden > states_Trump) {
			states_need = ((states_need-(states_Biden - states_Trump))/2) + 1;
		} else {
			states_need = (states_need/2)+1;
		}

		////////////////////////////////////////////////////////////////////////

		
		if ((states_Trump-states_Biden) >= swing_votes) return -1;
		else if ((states_Biden - states_Trump) > swing_votes) return 0;
		else {
			int[][] m = new int [potential_states + 1][states_need + 1];
			for (int i = 1; i < potential_states + 1; i++) {
				m[i][0] = 0;
			}
			for (int i = 1; i < states_need +1; i++) {
				m[0][i] = Integer.MAX_VALUE;
			}


			for (int i = 1; i <= potential_states; i++) {
				for (int j = 1; j < states_need + 1; j++) {
					if (potential_delegates[i-1] >= j) m[i][j] = Math.min(m[i-1][j], potential_undecided[i-1]);

					else if (  (m[i-1][j] == Integer.MAX_VALUE) && (m[i-1][j-potential_delegates[i-1]] == Integer.MAX_VALUE)  ) {
						m[i][j] = Integer.MAX_VALUE;
					}
					else {
						m[i][j] = Math.min(m[i-1][j], potential_undecided[i-1] + m[i-1][j - potential_delegates[i -1]]);
						
					}
				}
			}
			return m[potential_states][states_need];
		}
	}

	  public static int run_US_Elections (String path) {
		int answer = 0;
		try {

      		File myFile = new File(path);
      		Scanner sc = new Scanner(myFile);
      		int num_states = sc.nextInt();
      		int[] delegates = new int[num_states];
      		int[] votes_Biden = new int[num_states];
      		int[] votes_Trump = new int[num_states];
			int[] votes_Undecided = new int[num_states];
			 	
      			for (int state = 0; state<num_states; state++){
			  		delegates[state] =sc.nextInt();
					votes_Biden[state] = sc.nextInt();
					votes_Trump[state] = sc.nextInt();
					votes_Undecided[state] = sc.nextInt();
      			}
      		sc.close();
      		answer = solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
    	} catch (FileNotFoundException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
		}
		return answer;
	  }
}