package util;

import project2.Section;
import project2.Student;

/**
 * Sorting utility for Project 2.
 * Only static methods that take a List parameter.
 * No printing in this class.
 */
public class Sort {

    /**
     * Generic insertion sort for Comparable elements.
     */
    public static <E extends Comparable<E>> void sort(List<E> list) {
        if (list == null || list.size() <= 1) return;

        for (int i = 1; i < list.size(); i++) {
            E key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    /**
     * Sort students by last name, first name, then DOB.
     * Uses Student.compareTo(...) for name ordering, then DOB tiebreak.
     */
    public static void sortStudents(List<Student> list) {
        if (list == null || list.size() <= 1) return;

        for (int i = 0; i < list.size(); i++) {
            int min = i;

            for (int j = i + 1; j < list.size(); j++) {
                int cmp = list.get(j).compareTo(list.get(min));

                if (cmp < 0) {
                    min = j;
                } else if (cmp == 0) {
                    Date minDob = list.get(min).getProfile().getDob();
                    Date jDob  = list.get(j).getProfile().getDob();

                    if (jDob.compareTo(minDob) < 0) {
                        min = j;
                    }
                }
            }

            Student tmp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, tmp);
        }
    }

    /**
     * Sort sections by campus then building (insertion sort).
     */
    public static void sortByClassroom(List<Section> list) {
        if (list == null || list.size() <= 1) return;

        for (int i = 1; i < list.size(); i++) {
            Section key = list.get(i);
            String keyCampus = key.getClassroom().getCampus();
            String keyBuilding = key.getClassroom().getBuilding();

            int j = i - 1;

            while (j >= 0) {
                String campusJ = list.get(j).getClassroom().getCampus();
                String buildingJ = list.get(j).getClassroom().getBuilding();

                int campusCompare = campusJ.compareToIgnoreCase(keyCampus);

                boolean shouldShift =
                        (campusCompare > 0) ||
                                (campusCompare == 0 && buildingJ.compareToIgnoreCase(keyBuilding) > 0);

                if (!shouldShift) break;

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    /**
     * Sort sections by course number then period (insertion sort).
     */
    public static void sortByCourse(List<Section> list) {
        if (list == null || list.size() <= 1) return;

        for (int i = 1; i < list.size(); i++) {
            Section key = list.get(i);
            String keyCourseNum = key.getCourse().getCourseNum();
            int keyPeriod = key.getTime().getPeriodNum();

            int j = i - 1;

            while (j >= 0) {
                String courseNumJ = list.get(j).getCourse().getCourseNum();
                int periodJ = list.get(j).getTime().getPeriodNum();

                int courseCompare = courseNumJ.compareToIgnoreCase(keyCourseNum);

                boolean shouldShift =
                        (courseCompare > 0) ||
                                (courseCompare == 0 && periodJ > keyPeriod);

                if (!shouldShift) break;

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    private Sort() {}
}