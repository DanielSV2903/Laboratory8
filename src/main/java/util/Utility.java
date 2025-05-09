package util;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

    public class Utility {
        private static Random random;

        //constructor estatico, inicializador estatico
        static {
            // semilla para el random
            long seed = System.currentTimeMillis();
            random = new Random(seed);
        }
        public static int random(int bound){
            //return (int)Math.floor(Math.random()*bound); //forma 1
            return 1+random.nextInt(bound);
        }
        public static int random(int bound1,int bound2){
            //return (int)Math.floor(Math.random()*bound); //forma 1
            return bound1 + random.nextInt(bound2 - bound1 + 1);
        }

        public static void fill(int[] a) {
            for (int i = 0; i < a.length; i++) {
                a[i] = random(99);
            }
        }

        public static String format(long n) {
            return new DecimalFormat("###,###,###.##").format(n);
        }

        public static int min(int x, int y) {
            return x<y ? x : y;
        }

        public static int max(int x, int y) {
            return x>y ? x : y;
        }

        public static String show(int[] a) {
            String result="";
            for(int item : a){
                if(item == 0) break; //si es cero es xq no hay mas elementos
                result+=item + " ";
            }
            return result;
        }

        public static int compare(Object a, Object b) {
            switch(instanceOf(a, b)){
                case "Integer":
                    Integer int1 = (Integer)a; Integer int2 = (Integer)b;
                    return int1 < int2 ? -1 : int1 > int2 ? 1 : 0;

                case "String":
                    String str1 = (String)a; String str2 = (String)b;
                    return str1.compareTo(str2) < 0 ? -1 : str1.compareTo(str2) > 0 ? 1 : 0;

                case "Character":
                    Character ch1 = (Character) a; Character ch2 = (Character) b;
                    return ch1.compareTo(ch2) < 0 ? -1 : ch1.compareTo(ch2) > 0 ? 1 : 0;

//            case "Employee":
//                Employee st1 = (Employee) a; Employee st2 = (Employee) b;
//                return st1.getId()<st2.getId() ? -1 :  st1.getId()>st2.getId()  ? 1 : 0;
//
//            case "JobPosition":
//                JobPosition jp1 = (JobPosition) a; JobPosition jp2 = (JobPosition) b;
//                return jp1.getId()<jp2.getId() ? -1 :  jp1.getId()>jp2.getId()  ? 1 : 0;
//
//            case "Staffing":
//                Staffing stf1 = (Staffing) a; Staffing stf2 = (Staffing) b;
//                return stf1.getId()<stf2.getId() ? -1 : stf1.getId() > stf2.getId() ? 1 : 0;
            }
            return 2; //Unknown
        }

        public static String instanceOf(Object a, Object b){
            if(a instanceof Integer && b instanceof Integer) return "Integer";
            if(a instanceof String && b instanceof String) return "String";
            if(a instanceof Character && b instanceof Character) return "Character";
//        if(a instanceof Employee && b instanceof Employee) return "Employee";
//        if(a instanceof JobPosition && b instanceof JobPosition) return "JobPosition";
//        if(a instanceof Staffing && b instanceof Staffing) return "Staffing";
            return "Unknown";
        }
        private static void mostrarAlerta(String mensaje) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de validación");
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);
            alerta.showAndWait();
        }
        public static String dateFormat(Date birthDay) {
            return new SimpleDateFormat("d/M/yyyy").format(birthDay);
        }
        public static String date_Hours_Format(LocalDateTime birthDay) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm:ss");
            return birthDay.format(formatter);
        }
        public static int getAge(Date birthDay) {
            LocalDate birth=birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now=LocalDate.now();
            return Period.between(birth,now).getYears();
        }
        public static int maxArray(int[] a) {
            int max = a[0]; //first element
            for (int i = 1; i < a.length; i++) {
                if(a[i]>max){
                    max=a[i];
                }
            }
            return max;
        }


        public static boolean validarEntradasArray(TextField lengthField, TextField lowField, TextField highField) {

            String lengthText = lengthField.getText().trim();
            try {
                int length = Integer.parseInt(lengthText);
                if (length <= 0 || length > 200) {
                    mostrarAlerta("La longitud del arreglo debe estar entre 1 y 200.");
                    return false;
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("La longitud del arreglo debe ser un número entero.");
                return false;
            }

            String lowText = lowField.getText().trim();
            String highText = highField.getText().trim();
            try {
                int low = Integer.parseInt(lowText);
                int high = Integer.parseInt(highText);

                if (low > high) {
                    mostrarAlerta("El límite inferior no puede ser mayor que el límite superior.");
                    return false;
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Los límites deben ser números enteros válidos.");
                return false;
            }

            return true;
        }


        private static Object determinarLetra(int value) {
            switch (value) {
                case 10:return "A";
                case 11:return "B";
                case 12:return "C";
                case 13:return "D";
                case 14:return "E";
                case 15:return "F";
                default:return "0";
            }
        }


        private static List<String> personPriorities = new ArrayList<>();

        public static List<String> getPersonPriorities() {
            return personPriorities;
        }

        public static void setPersonPriorities(List<String> personPriorities) {
            Utility.personPriorities = personPriorities;
        }

        private static int prioritySelection(String priority){
            int selectedIndex;
            switch(priority){
                case "high":
                    selectedIndex=3;
                    break;
                case "medium":
                    selectedIndex=2;
                    break;
                case "low":
                    selectedIndex=1;
                    break;
                default:
                    selectedIndex=0;
                    break;
            }
            return selectedIndex;
        }


        public static int[] getIntegerArray(int i) {
            int [] array =new int[i];
            int length=array.length;
            for (int j=0;j<length;j++){
                array[j]=random(9999);
            }
            return array;
        }

        public static int[] copyArray(int[] a) {
            int length=a.length;
            int[] copy=new int[length];
            for (int i=0;i<length;i++)
                copy[i]=a[i];
            return copy;
        }

        public static int[] createArray(int lengthText, int lowBoundText, int highBoundText) {
            int[] array=new int[lengthText];
            int length=array.length;
            for (int j=0;j<length;j++){
                array[j]=random(lowBoundText,highBoundText);
            }
            return array;
        }
    }