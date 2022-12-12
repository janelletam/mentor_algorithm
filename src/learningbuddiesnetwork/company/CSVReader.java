package learningbuddiesnetwork.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

public class CSVReader {

    static BufferedReader bufferedReader;
    static StringTokenizer tokenizer;

    public static ArrayList<Mentor> readMentorsFromCSV(String fileName) throws IOException {
        ArrayList<Mentor> listOfMentors = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        bufferedReader = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII);

        String line = nextLine();
        while (!line.equals("null")) { //!!!

            // Use string.split to load a string array with the values from
            // each line of the file, using a comma as the delimiter
            String[] attributes = line.split(",");
            Mentor mentor = createMentor(attributes);

            // adding Mentor into ArrayList
            listOfMentors.add(mentor);

            // Read next line before looping. If end of file reached, line would be null
            line = nextLine();
        }

        //we can simply the try catch by having an IOException

//        // Create an instance of BufferedReader
//        // Using try with resource, Java 7 feature to close resources
//        try (BufferedReader br = Files.newBufferedReader(pathToFile,
//                StandardCharsets.US_ASCII)) {
//
//            // Read the first line from the text file
//            String line = f.readLine();
//
//            // Loop until all lines are read
//            while (line != null) {
//
//                // Use string.split to load a string array with the values from
//                // each line of the file, using a comma as the delimiter
//                String[] attributes = line.split(",");
//                Mentor mentor = createMentor(attributes);
//
//                // adding Mentor into ArrayList
//                listOfMentors.add(mentor);
//
//                // Read next line before looping. If end of file reached, line would be null
//                line = br.readLine();
//            }
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }

        bufferedReader.close();
        return listOfMentors;
    }

    private static Mentor createMentor(String[] attributes) {
        String firstName = attributes[0];
        String preferredName = attributes[1];
        String lastName = attributes[2];

        String age = attributes[3];
        String phoneNumber = attributes[4];
        String email = attributes[5];
        String pronouns = attributes[6];
        String school = attributes[7];
        String yearsAttended = attributes[8];
        String major = attributes[9];

        //Todo: Join these fields together
        String emergencyContactName;
        int i = 0;
        if (attributes.length == 20) {
            emergencyContactName = attributes[10] + attributes[11];
            i = 1;
        } else {
            emergencyContactName = attributes[10];
        }

        String emergencyContactEmail = attributes[11 + i];
        String emergencyContactNumber = attributes[12 + i];

        String isReadingMentor = attributes[13 + i];

        String availabilityOnline = attributes[14 + i];
        String availabilityInPerson = attributes[15 + i];
        String availabilityClayton = attributes[16 + i];

        String isReturning = attributes[17 + i];
        String isPhotoVideoConsentTrue = attributes[18 + i];

        // Create and return Mentor with these attributes
        return new Mentor(firstName, preferredName, lastName, age, phoneNumber, email, pronouns,
                school, yearsAttended, major, emergencyContactName, emergencyContactEmail,
                emergencyContactNumber, isReadingMentor, availabilityOnline,
                availabilityInPerson, availabilityClayton, isReturning, isPhotoVideoConsentTrue);
    }


    static String next() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        }
        return tokenizer.nextToken();
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static char nextCharacter() throws IOException {
        return next().charAt(0);
    }

    static String nextLine() throws IOException {
        String s = bufferedReader.readLine();
        if (s == null) {
            return "null";
        }
        return s.trim();

    }

}