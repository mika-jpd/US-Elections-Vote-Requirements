public class US_elections_tester {

    public static void main(String[] args) {
        System.out.println("Start of test :");
        
        int results[] = {156, 26, 24, 32, 28, 11099, -1, 146, -1, 246, -1, 0, 4, 1, 50, -1, -1};
        int grade = 0;
        for (int i = 1; i <= 17; i++) {
            //Insert the absolute path to the folder containing the test files
            String abs_path = "...\\tests\\";
            String path = abs_path + "open_case_" + i + ".txt";
            int res = US_elections.run_US_Elections(path);
            if (results[i-1] == res) {
                System.out.println("----------------\nTest Case " + i + " :\nPASSED" + "\n    expected result : " + results[i -1] + "\n    user result : " + res);
                ++grade;
            } else {
                System.out.println("Test Case " + i + " :\nFAILED" + "\n    expected result : " + results[i -1] + "\n    user result : " + res);
            }
        }
        
        System.out.println("\n\n----------------\nFINAL GRADE: " + grade + "/" + results.length);
    }
}
