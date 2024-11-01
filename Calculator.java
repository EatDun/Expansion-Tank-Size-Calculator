import java.util.Scanner;

class Calculator {
    
    //Calculates air cushion or diaphragm pressure
    public static double pressCalc (double h, double c) {
        return 5 + ((c / 144) * h);
    }
    
    //Calculates size of expansion tank in gallons
    public static double sizeCalc (int t, double v, double c, double h, double p, double r) {
        if (t == 1) {
            return (v * ((c / h) - 1)) / (14.7 * ((1 / (p + 14.7)) - (1 / (r + 14.7))));
        }
        else if (t == 2) {
            return ((c / h) - 1) * ((r + 9.7) / (r - p - 5)) * v;
        }
        else {
            return 420.69;
        }
    }
    
    //Calculates density of water based on temperature
    public static double denseCalc (double t) {
        return (-.018 * t) + 63.42;
    }
    
    public static void main(String[] args) {
        
        //Create scanner for inputs
        Scanner input = new Scanner(System.in);
        
        System.out.println("Press 1 for Air Cushion:\nType 2 for Diaphragm:");
        
        int type = input.nextInt();
        
        //Verifying type input
        if (type > 2 || type < 0) {
            System.out.println("INVALID SELCECTION");
            return;
        }
        
        //Ask for distance between top of expansion tank and highest system point
        System.out.println("Please enter height from top of expansion tank:");
        
        double height = input.nextDouble();
        
        //Asking for total system volume
        System.out.println("Please enter system volume:");
        
        double volume = input.nextDouble();
        
        //Asking for cold water temperature
        System.out.println("Please enter cold water temperature:");
        
        double coldTemp = input.nextDouble();
        
        //Asking for hot water temperature
        System.out.println("Please enter hot water temperature:");
        
        double hotTemp = input.nextDouble();
        
        //Asking for pressure relief valve setting in PSIG
        System.out.println("Please enter pressure relief valve setting:");
        
        double relief = input.nextDouble();
        
        //Using methods to create dependant inputs
        double coldDense = denseCalc(coldTemp);
        double hotDense = denseCalc(hotTemp);
        double pressure = pressCalc(height, coldDense);
        double tankSize = sizeCalc(type, volume, coldDense, hotDense, pressure, relief);
        
        //Returning calculations
        System.out.println(type);
        
        System.out.printf("Calculated Cold Water Density: %.5f psi%n", coldDense);
        
        System.out.printf("Calculated Hot Water Density: %.5f PSI%n", hotDense);
        
        System.out.printf("Calculated Pressure: %.2f psi%n", pressure);
        
        System.out.printf("Calculated Tank Size: %.2f gallons%n", tankSize);
    }
}
