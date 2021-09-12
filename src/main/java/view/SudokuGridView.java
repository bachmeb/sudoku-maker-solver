package view;

public class ASDF {
    public static String intArrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();

        if (arr == null) {
            return "[]";
        }

        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
