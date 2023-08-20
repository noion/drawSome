public class Main {
    public static void main(String[] args) {
        var height = 30;
        var width = 60;
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 20; j++) {
//                System.out.print("@");
//            }
//            System.out.println("");
//        }
        //draw a triangle
        for (int i = 0; i < height; i++) {
            if (i == 0) {
                System.out.print("@");
            } else if (i == height - 1) {
                for (int j = 0; j < width; j++) {
                    System.out.print("@");
                }
            } else {
                for (int j = 0; j < width; j++) {
                    if (j == i || j == width - i - 1) {
                        System.out.print("@");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                for (int j = 0; j < width; j++) {
                    System.out.print("@");
                }
            } else {
                for (int j = 0; j < width; j++) {
                    if (j == 0 || j == width - 1) {
                        System.out.print("@");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }
}
