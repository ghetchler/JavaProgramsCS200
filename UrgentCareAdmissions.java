//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This program automates patient management for a hospital
// Course:   CS 300 Spring 2023
//
// Author:   Garrett Hetchler
// Email:    ghetchler@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    none
// Partner Email:   none
// Partner Lecturer's Name: none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

public class UrgentCareAdmissions {
    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;

    /**
     * This helps find the correct place to add a new patient based off their given triage level
     *
     * @param triage       This is the urgency level of the next patient's and can be RED, YELLOW,
     *                     or GREEN
     * @param patientsList This is the list of the urgency of the patients that need treatment
     *                     starting with RED and progressing to GREEN
     * @param size         This is the number of patients that need treatment
     * @return This returns the position that the new patient will be added to based on triage value
     * or -1 if the list is full
     */
    public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
        if (size == patientsList.length || size == 0) {
            return -1;
        }
        int counter = 0;
        for (int row = 0; row < patientsList.length; row++) {
            if (patientsList[row][2] == 0) {
                if (triage == 0) {
                    while (patientsList[row][2] == 0) {
                        for (int col = 0; col < patientsList[row].length; col++) {
                            if (patientsList[row][col] > triage) {
                                ++counter;
                            }
                        }
                        return counter - 1;
                    }
                }
                if (triage == 1) {
                    while (patientsList[row][2] != 2) {
                        for (int col = 0; col < size; col++) {
                            counter++;
                        }
                        return counter - 1;
                    }
                }
                if (triage == 2) {
                    return size;
                }
            }
            if (patientsList[row][2] == 1) {
                if (triage == 0) {
                    return 0;
                }
                if (triage == 1) {
                    while (patientsList[row][2] == 1) {
                        for (int col = 0; col < patientsList[row].length; col++) {
                            if (patientsList[row][col] > triage) {
                                ++counter;
                            }
                        }
                        return counter - 1;
                    }
                }
                if (triage == 2) {
                    return size;
                }
            }
            if (patientsList[row][2] == 2) {
                if (triage == 0) {
                    return 0;
                }
                if (triage == 1) {
                    return 0;
                }
                if (triage == 2) {
                    return size;
                }
            }
        }
        return -1;
    }

    /**
     * This adds a patient's record to the list of patients
     *
     * @param patientRecord This is a list of the patient's 5 digit case ID, admission order, and
     *                      triage level
     * @param index         This is the order that the patient's record should be added to the list
     *                      of patients
     * @param patientsList  This is the list of the urgency of the patients that need treatment
     *                      starting with RED and progressing to GREEN
     * @param size          This is the number of patients that need treatment
     * @return
     */
    public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
        if (patientsList.length == size || index > patientsList.length) return size;

        for (int row = size; row > index; row--) {
            patientsList[row] = patientsList[row - 1];
        }
        patientsList[index] = patientRecord;

        return size + 1;
    }

    /**
     * This removes the first patient on the list and moves everyone else up one spot
     *
     * @param patientsList This is the list of the urgency of the patients that need treatment
     *                     starting with RED and progressing to GREEN
     * @param size         This is the number of patients that need treatment
     * @return This returns the new number of patients after dropping one
     */
    public static int removeNextPatient(int[][] patientsList, int size) {
        if (size == 0) return size;
        for (int row = 0; row < patientsList.length - 1; row++) {
            patientsList[row] = patientsList[row + 1];
        }

        return size - 1;
    }

    /**
     * This gets the index of the patient that is being looked for
     *
     * @param caseID       This is the 5 digit number assigned to each patient
     * @param patientsList This is the list of the urgency of the patients that need treatment
     *                     starting with RED and progressing to GREEN
     * @param size         This is the number of patients that need treatment
     * @return This returns the index of the patient with the matching caseID and -1 if the list is
     * empty or the caseID was not found
     */
    public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
        if (size == 0) return -1;
        int targetIndex = -8;
        for (int row = 0; row < size; row++) {
            if (caseID == patientsList[row][0]) {
                targetIndex = row;
            }
        }
        if (targetIndex == -8) return -1;

        return targetIndex;
    }

    /**
     * This finds the patient that has been waiting the longest
     *
     * @param patientsList This is the list of the urgency of the patients that need treatment
     *                     starting with RED and progressing to GREEN
     * @param size         This is the number of patients that need treatment
     * @return This returns the index of the patient that has been waiting the longest and -1 if the
     * list is empty
     */
    public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
        if (size == 0) {
            return -1;
        }
        int longestWait = 5;
        int minWait = 5;
        for (int row = 0; row < size; row++) {
            if (patientsList[row][1] < longestWait) {
                longestWait = patientsList[row][1];
                minWait = row;            }
        }
        return minWait;
    }

    /**
     * This prints out a summary of how many patients there are and how many of each triage levels
     * there are
     *
     * @param patientsList This is the list of the urgency of the patients that need treatment
     *                     starting with RED and progressing to GREEN
     * @param size         This is the number of patients that need treatment
     * @return This returns a summary of the total number of patients and the total number of each
     * triage level.
     */
    public static String getSummary(int[][] patientsList, int size) {
        int redCount = 0;
        int yellowCount = 0;
        int greenCount = 0;
        if (size == 0){
            String summary = "Total number of patients: " + size + "\nRED: " + redCount +
                    "\nYELLOW: " + yellowCount + "\nGREEN: " + greenCount;
            return summary;
        }
        for (int row = 0; row < size; row++) {
            if (patientsList[row][2] == 0) {
                ++redCount;
            }else if (patientsList[row][2] == 1) {
                ++yellowCount;
            }else if (patientsList[row][2] == 2) {
                ++greenCount;
            }

        }

        String summary = "Total number of patients: " + size + "\nRED: " + redCount +
    "\nYELLOW: " + yellowCount + "\nGREEN: " + greenCount;

        return summary;
    }


}

