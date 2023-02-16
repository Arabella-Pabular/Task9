import java.awt.Font;

import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javax.swing.JTextArea;

public class ApplyGovernmentDeductions {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		JTextArea myArea = new JTextArea(15,15);
		DecimalFormat f= new DecimalFormat("P00,000.00");
		DecimalFormat p= new DecimalFormat("P 0,000.00");
		DecimalFormat h= new DecimalFormat("000.00");
		
		final int STANDARD_WORK=176; // Monthly standard work hours 
		String Enumber;
		String Name;
		double hoursWorked;
		double hourlyRate;
		double basicPay;
		double overTime=0;
		double overTimeRate=0;
		double overTimePay=0;
		double grossSalary=0;      //Gross Salary without deductions
	    double Tax=0;            //with holding tax
	    double philHealth=0.015;  // PhilHealth Deduction with 50% Employer's contribution
	    double SSS=0.04;          // SSS Deduction percentage
	    double PagIbig=0.02;     // PagIbig Fund Deduction percentage
	    double totaldeduct=0;    // Total Deductions 
	    double netSalary=0;      // Taxable Income
	        
	   
	    
	    double DeductSSS = grossSalary * SSS;
	    double DeductPf = grossSalary * PagIbig;
	    double DeductPh = grossSalary * philHealth;
	    double totalsalary = 0; // Final Salary after tax deduction
	    
	    
	    
		
		System.out.print("Enter an Employee Number: ");
		Enumber = keyboard.nextLine();
		
		System.out.print("Enter an Employee Name: ");
		Name = keyboard.nextLine();
		
		System.out.print("Enter the no. of Hours Worked: ");
		hoursWorked = keyboard.nextDouble();
		
		System.out.print("Enter the hourlyRate: ");
		hourlyRate = keyboard.nextDouble();
		
		//Process
		if(hoursWorked <= STANDARD_WORK) {
			basicPay = hoursWorked * hourlyRate;
			grossSalary = basicPay;
			
		}
		else {
			basicPay = STANDARD_WORK * hourlyRate;
			overTime = hoursWorked - STANDARD_WORK;
			overTimeRate = hourlyRate * 1.5; 
			overTimePay = overTimeRate * overTime; 
			grossSalary = basicPay + overTimePay;
		}
		
		//Deductions
		if(grossSalary > 1500){ 
			DeductSSS = grossSalary * SSS;
	        DeductPf = grossSalary * PagIbig;
	        DeductPh = grossSalary * philHealth;
	        totaldeduct = DeductSSS + DeductPf + DeductPh;
			grossSalary = basicPay + overTimePay;
			netSalary = grossSalary -totaldeduct;
		}
		else
		{	   
		   netSalary = grossSalary - totaldeduct;
		   totalsalary = netSalary - Tax;
		}
		
		
		if(netSalary <=20832)
		{
			System.out.print(netSalary+"No Tax");
		}
		else if(netSalary>20833 && netSalary<=33333)
		{
			Tax = (netSalary-20833) * .2;
			totalsalary = netSalary - Tax;
		}
		else if(netSalary>33333 && netSalary<=66667)
		{
			Tax = ((netSalary-33333)*.25)+2500;	
			totalsalary = netSalary - Tax;
		}
		else if(netSalary>66667 && netSalary<=166667)
		{
			Tax = ((netSalary-66667)*.3)+10833;
			totalsalary = netSalary - Tax;
		}
		else if(netSalary>166667 && netSalary<=666667)
		{
			Tax = ((netSalary-166667)*.32)+408833.33;
			totalsalary = netSalary - Tax;
		}
		else if(netSalary>666667)
		{
			Tax = ((netSalary-666667)*.35)+200833.33;
			totalsalary = netSalary - Tax;
		}
		
		 
			

		
		
		
		//Print PaySlip
		myArea.setEditable(false);
		myArea.setFont(new Font("Arial",Font.PLAIN,28));
		myArea.setText(
		    "\tMOTORPH COMPANY\n\t PAYSLIP\n\n=============================================" +
			"\nEmployee Number:\t" + Enumber +
			"\nName:\t\t" + Name +
			"\nHours Worked:\t\t" + hoursWorked +
			"\nHourly Rate:\t\t" + hourlyRate +
			"\nOver Time:\t\t" + overTime +
			"\nOver Time Rate:\t" + h.format(overTimeRate) +
			"\nOver Time Pay:\t\t" + f.format(overTimePay) +
			"\nBasic Pay:\t\t" + f.format(basicPay) +
			
			"\n\nGross Salary:\t\t\t" + f.format(grossSalary) +
			"\n\nSSS:\t\t" + p.format(DeductSSS) +
		    "\nPhil Health Deduction:\t" + p.format(DeductPh) +
		    "\nPag-ibig Fund Deduction:\t" + p.format(DeductPf)+
		    "\n\nTOTAL DEDUCTIONS:\t\t" + p.format(totaldeduct) +
		    "\n\nTaxable Income:\t\t" + f.format(netSalary)+
		    "\n\nWithholding Tax:\t\t" + f.format(Tax) +
		    "\n--------------------------------------------------------------------------------\n\n" +
		    "\n\nMONTHLY SALARY:\t\t" + f.format(totalsalary));
		
		
				
		JOptionPane.showMessageDialog(
				null, myArea, "Payslip", JOptionPane.PLAIN_MESSAGE);
		
		
		//Print Payroll Report
				System.out.println();
				System.out.printf("%45s\n","MotorPH Company");
				System.out.printf("%50s\n\n","MONTHLY PAYROLL REPORT");
				System.out.printf("%-8s%-12s%-8s%-6s%-6s%-8s%-11s%-11s%-11s\n", 
						"Enumber","Name","    HWorked","  HRate","  OT","  OTRate","  OTPay"," BasicPay",
						"   GrossSalary");
				System.out.printf(
						"%-8S%-18S%-8.1f%-6.1f%-6.1f%-8.1fP%,-11.2fP%,-11.2fP%-,11.2f", 
							Enumber,Name, hoursWorked, hourlyRate, overTime, overTimeRate,
							overTimePay, basicPay, grossSalary);
	
	
	
	
	
	
	}
}
