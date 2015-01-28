package ru.itpark;

import java.util.Random;
import java.util.Scanner;

public class Main {

	
	
	public static void main(String[] args) {
		
		int sizeX = 10;
		int sizeY = 10;
		Random rand = new Random();
		
		//карта кораблей
		char mas[][] = new char[sizeX][sizeY];
		
		//поле боя
		char battleField[][] = new char[sizeX][sizeY];
		
		for(int x = 0 ; x < mas.length; x++)
			for(int y = 0; y < mas[x].length; y++)
				mas[x][y] = '0';
	
		for(int x = 0 ; x < battleField.length; x++)
			for(int y = 0; y < battleField[x].length; y++)
				battleField[x][y] = '0';
			
		genFourPalube1234Ships(mas, 4, 1);
		genFourPalube1234Ships(mas, 3, 2);
		genFourPalube1234Ships(mas, 2, 3);
		genFourPalube1234Ships(mas, 1, 4);
		
		int countNoZeroCells = 0;
		
		for(int x = 0 ; x < mas.length; x++)
			for(int y = 0; y < mas[x].length; y++)
				if(mas[x][y] != '0') countNoZeroCells++;
		
	/*	
		System.out.println("------------------------------------------");	
		for(int x = 0; x < mas.length; x++)
		{	
			for(int y = 0; y < mas[x].length; y++)
				System.out.print(mas[x][y] + " " );
			System.out.println();
		}
	
	*/
		
		int popitka = 0;
		int countShipCells = 0;
		Scanner sc = new Scanner(System.in);
		
		while(popitka < 101)
		{
			System.out.println("------------------------------------------");	
			System.out.print("\t");
			for(int y = 0; y < battleField[0].length; y++)
				System.out.print((y + 1) + " ");
			System.out.print("\n\n");
			for(int x = 0; x < battleField.length; x++)
			{	
				for(int y = 0; y < battleField[x].length; y++)
				{	
					if(y == 0) System.out.print((x + 1) + "\t");
						System.out.print(battleField[x][y] + " " );
						if(x == battleField.length - 1 &&
								y == battleField[x].length - 1)
							System.out.println("        Подбито палуб = " + 
								+ countShipCells + " Всего палуб = " + countNoZeroCells 
								+ "  Количество попыток = " + (101 - popitka));
				}
				
				System.out.println();
			}
			if(countNoZeroCells == countShipCells)
			{	
				System.out.println("------ Победа! Все корабли уничтожены !!! -------");
				break;
			}
			System.out.print("Ведите строку = ");
			int xt = sc.nextInt() - 1;
			System.out.print("Ведите столбец = ");
			int yt = sc.nextInt() - 1;
			
			battleField[xt][yt] = 'X';
			if(mas[xt][yt] != '0')
			{
				battleField[xt][yt] = 'S';
				
			}
			countShipCells = 0;
			for(int x = 0; x < battleField.length; x++)
			{	
				for(int y = 0; y < battleField[x].length; y++)
					if(battleField[x][y] == 'S') countShipCells++;
			}
			
			popitka++;
			if(popitka >= 101 && countNoZeroCells > 0)
				System.out.println("Game over!!!");
		}
		
	}
	
	/*
	public static void  genOnePalubeShips(char mas[][], int count){
		Random rand = new Random();
		int countShips = 0;
		while(countShips < count)
		{
			int shipx = rand.nextInt(mas.length);
			int shipy = rand.nextInt(mas.length);
			if(mas[shipx][shipy] == '0')
			{
				if(shipx > 0 && shipy > 0 && 
				   shipx < mas.length - 1 && shipy < mas.length - 1)
				{
					if(mas[shipx - 1][shipy] =='0')
					if(mas[shipx - 1][shipy - 1] =='0')
					if(mas[shipx][shipy - 1] =='0')
					if(mas[shipx + 1][shipy - 1] =='0')
					if(mas[shipx + 1][shipy] =='0')
					if(mas[shipx + 1][shipy + 1] =='0')
					if(mas[shipx][shipy + 1] =='0')
					if(mas[shipx - 1][shipy + 1] =='0')
						{mas[shipx][shipy] = '1'; countShips++;}
				}
				if(shipx == 0 && shipy == 0)
				{
					if(mas[shipx + 1][shipy] =='0')
					if(mas[shipx + 1][shipy + 1] =='0')
					if(mas[shipx][shipy + 1] =='0')
						{mas[shipx][shipy] = '1'; countShips++;}
				}
				if(shipx == mas.length - 1 && shipy == 0)
				{
					if(mas[shipx - 1][shipy] =='0')
					if(mas[shipx][shipy + 1] =='0')
					if(mas[shipx - 1][shipy + 1] =='0')
						{mas[shipx][shipy] = '1'; countShips++;}
				}
				if(shipx == mas.length - 1 && shipy == mas.length - 1)
				{
					if(mas[shipx - 1][shipy] =='0')
					if(mas[shipx - 1][shipy - 1] =='0')
					if(mas[shipx][shipy - 1] =='0')
						{mas[shipx][shipy] = '1'; countShips++;}
				}
				if(shipx == 0 && shipy == mas.length - 1)
				{
					if(mas[shipx][shipy - 1] =='0')
					if(mas[shipx + 1][shipy - 1] =='0')
					if(mas[shipx + 1][shipy] =='0')
						{mas[shipx][shipy] = '1'; countShips++;}
				}
			}
		}
	}
	
	public static void genFourPalubeShips(char mas[][], int count)
	{
		Random rand = new Random();
		int countGeneration = 0;
		int countShips = 0;
		while(countShips < count)
		{
			if(countGeneration > 2000) break; 
			countGeneration++;
			int variant = rand.nextInt(2);
			variant = 1;
			if(variant == 1)
			{
				int shipx = rand.nextInt(mas.length - 3);
				int shipy = rand.nextInt(mas.length);
				if(mas[shipx][shipy] == '0')
				{
					if(shipx > 0 && shipy > 0 && 
							shipx < mas.length - 1 - 3 && shipy < mas.length - 1)
						{
							if(mas[shipx - 1][shipy - 1] =='0')
							if(mas[shipx - 1][shipy] =='0')
							if(mas[shipx - 1][shipy + 1] =='0')
						
							if(mas[shipx + 4][shipy - 1] =='0')
							if(mas[shipx + 4][shipy] =='0')
							if(mas[shipx + 4][shipy + 1] =='0')
							{	
								int k = 0;	
								for(int x = shipx; x < shipx + 4; x++)
								{
									if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0' 
									&& mas[x][shipy + 1] == '0') k++;
								}
								if(k == 4) 
								{
									 countShips++;
									for(int x = shipx; x < shipx + 4; x++)
									{
										mas[x][shipy] = '4';
									}
									
								}
							}	
						}
					if(shipx == 0 && shipy > 0 && shipy < mas.length - 1)
						{
							if(mas[shipx + 4][shipy - 1] =='0')
							if(mas[shipx + 4][shipy] =='0')
							if(mas[shipx + 4][shipy + 1] =='0')
							{	
								int k = 0;	
								for(int x = shipx; x < shipx + 4; x++)
								{
									if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0' 
									&& mas[x][shipy + 1] == '0') k++;
								}
								if(k == 4) 
								{
									 countShips++;
									for(int x = shipx; x < shipx + 4; x++)
									{
										mas[x][shipy] = '4';
									}
									
								}
							}	
						}
					
					if(shipx == mas.length - 1 - 3 && shipy > 0 && shipy < mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
						if(mas[shipx - 1][shipy + 1] =='0')
						
						if(mas[shipx + 3][shipy - 1] =='0')
						if(mas[shipx + 3][shipy] =='0')
						if(mas[shipx + 3][shipy + 1] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0' 
								&& mas[x][shipy + 1] == '0') k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}
					
					if(shipx == mas.length - 1 - 3 && shipy == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
												
						if(mas[shipx + 3][shipy - 1] =='0')
						if(mas[shipx + 3][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}
					if(shipx == 0 && shipy == mas.length - 1)
					{
						if(mas[shipx + 4][shipy - 1] =='0')
						if(mas[shipx + 4][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}
					if(shipx == mas.length - 1 - 3 && shipy == 0)
					{
						if(mas[shipx - 1][shipy + 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
												
						if(mas[shipx + 3][shipy + 1] =='0')
						if(mas[shipx + 3][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy + 1] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}
					if(shipx == 0 && shipy == 0)
					{
						if(mas[shipx + 4][shipy + 1] =='0')
						if(mas[shipx + 4][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy + 1] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}
					if(shipx > 0 && shipx < mas.length - 1 - 3 && shipy == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
															
						if(mas[shipx + 4][shipy - 1] =='0')
						if(mas[shipx + 4][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}if(shipx > 0 && shipx < mas.length - 1 - 3 && shipy == 0)
					{
						if(mas[shipx - 1][shipy + 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
															
						if(mas[shipx + 4][shipy + 1] =='0')
						if(mas[shipx + 4][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + 4; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy + 1] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + 4; x++)
								{
									mas[x][shipy] = '4';
								}
								
							}
						}	
					}
				}
			}	
			else
			{
				int shipx = rand.nextInt(mas.length );
				int shipy = rand.nextInt(mas.length - 3);
			
				if(mas[shipx][shipy] == '0')
				{
				
					if(shipx > 0 && shipy > 0 && 
							shipx < mas.length - 1  && shipy < mas.length - 1 - 3)
						{
							if(mas[shipx - 1][shipy - 1] =='0')
							if(mas[shipx][shipy - 1] =='0')
							if(mas[shipx + 1][shipy - 1] =='0')
						
							if(mas[shipx - 1][shipy + 4] =='0')
							if(mas[shipx][shipy + 4] =='0')
							if(mas[shipx + 1][shipy + 4] =='0')
							{	
								int k = 0;	
								for(int y = shipy; y < shipy + 4; y++)
								{
									if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0' && mas[shipx + 1][y] == '0') k++;
								}
								if(k == 4) 
								{
									 countShips++;
									for(int y = shipy; y < shipy + 4; y++)
									{
										mas[shipx][y] = '4';
									}
								}
							}	
						}
					if(shipy == 0 && shipx > 0 && shipx < mas.length - 1)
						{
					
							if(mas[shipx - 1][shipy + 4] =='0')
							if(mas[shipx][shipy + 4] =='0')
							if(mas[shipx + 1][shipy + 4] =='0')
							{	
								int k = 0;	
								for(int y = shipy; y < shipy + 4; y++)
								{
									if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0' 
									&& mas[shipx + 1][y] == '0') k++;
								}
								if(k == 4) 
								{
									 countShips++;
									for(int y = shipy; y < shipy + 4; y++)
									{
										mas[shipx][y] = '4';
									}
								}
							}	
						}
					
					if(shipy == mas.length - 1 - 3 && shipx > 0 && shipx < mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
						if(mas[shipx + 1][shipy - 1] =='0')
						
						if(mas[shipx - 1][shipy + 3] =='0')
						if(mas[shipx][shipy + 3] =='0')
						if(mas[shipx + 1][shipy + 3] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0' 
								&& mas[shipx + 1][y] == '0') k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
							}
						}	
					}
				
					if(shipy == mas.length - 1 - 3 && shipx == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
												
						if(mas[shipx - 1][shipy + 3] =='0')
						if(mas[shipx][shipy + 3] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
							}
						}	
					}
					
					
					if(shipy == 0 && shipx == mas.length - 1)
					{
						if(mas[shipx - 1][shipy + 4] =='0')
						if(mas[shipx][shipy + 4] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
							}
						}	
					}
					if(shipy == mas.length - 1 - 3 && shipx == 0)
					{
						if(mas[shipx + 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
												
						if(mas[shipx + 1][shipy + 3] =='0')
						if(mas[shipx][shipy + 3] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx + 1][y] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
								
							}
						}	
					}
					
					if(shipx == 0 && shipy == 0)
					{
						if(mas[shipx + 1][shipy + 4] =='0')
						if(mas[shipx][shipy + 4] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx + 1][y] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
								
							}
						}	
					}
					
					if(shipy > 0 && shipy < mas.length - 1 - 3 && shipx == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
															
						if(mas[shipx - 1][shipy + 4] =='0')
						if(mas[shipx][shipy + 4] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
								
							}
						}	
					}
					
				
					if(shipy > 0 && shipy < mas.length - 1 - 3 && shipx == 0)
					{
						if(mas[shipx + 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
															
						if(mas[shipx + 1][shipy + 4] =='0')
						if(mas[shipx][shipy + 4] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + 4; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx + 1][y] == '0'
										) k++;
							}
							if(k == 4) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + 4; y++)
								{
									mas[shipx][y] = '4';
								}
								
							}
						}	
					}
					
				}
			}	
		}
		System.out.println("countGeneration = " + countGeneration);
	}
	*/
	public static void genFourPalube1234Ships(char mas[][], int countPalubes, int count)
	{
		Random rand = new Random();
		int countGeneration = 0;
		int countShips = 0;
		while(countShips < count)
		{
			if(countGeneration > 2000) break; 
			countGeneration++;
			int variant = rand.nextInt(2);
			//variant = 0;
			if(variant == 1)
			{
				int shipx = rand.nextInt(mas.length - (countPalubes - 1));
				int shipy = rand.nextInt(mas.length);
				if(mas[shipx][shipy] == '0')
				{
					if(shipx > 0 && shipy > 0 && 
							shipx < mas.length  /*- 1 - 3*/ - countPalubes && shipy < mas.length - 1)
						{
							if(mas[shipx - 1][shipy - 1] =='0')
							if(mas[shipx - 1][shipy] =='0')
							if(mas[shipx - 1][shipy + 1] =='0')
						
							if(mas[shipx + countPalubes][shipy - 1] =='0')
							if(mas[shipx + countPalubes][shipy] =='0')
							if(mas[shipx + countPalubes][shipy + 1] =='0')
							{	
								int k = 0;	
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0' 
									&& mas[x][shipy + 1] == '0') k++;
								}
								if(k == countPalubes) 
								{
									 countShips++;
									for(int x = shipx; x < shipx + countPalubes; x++)
									{
										String ss = "";
										ss = Integer.toString(countPalubes);
										mas[x][shipy] =  ss.charAt(0);
									}
									
								}
							}	
						}
					
					if(shipx == 0 && shipy > 0 && shipy < mas.length - 1)
						{
							if(mas[shipx + countPalubes][shipy - 1] =='0')
							if(mas[shipx + countPalubes][shipy] =='0')
							if(mas[shipx + countPalubes][shipy + 1] =='0')
							{	
								int k = 0;	
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0' 
									&& mas[x][shipy + 1] == '0') k++;
								}
								if(k == countPalubes) 
								{
									 countShips++;
									for(int x = shipx; x < shipx + countPalubes; x++)
									{
										String ss = "";
										ss = Integer.toString(countPalubes);
										mas[x][shipy] =  ss.charAt(0);
									}
									
								}
							}	
						}
					
					if(shipx == mas.length - countPalubes && shipy > 0 && shipy < mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
						if(mas[shipx - 1][shipy + 1] =='0')
						
						if(mas[shipx + countPalubes - 1][shipy - 1] =='0')
						if(mas[shipx + countPalubes - 1][shipy] =='0')
						if(mas[shipx + countPalubes - 1][shipy + 1] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0' 
								&& mas[x][shipy + 1] == '0') k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
							}
						}	
					}
					
					if(shipx == mas.length - countPalubes && shipy == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
												
						if(mas[shipx + countPalubes - 1][shipy - 1] =='0')
						if(mas[shipx + countPalubes - 1][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
								
							}
						}	
					}
					
					if(shipx == 0 && shipy == mas.length - 1)
					{
						if(mas[shipx + countPalubes][shipy - 1] =='0')
						if(mas[shipx + countPalubes][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
								
							}
						}	
					}
					
					if(shipx == mas.length - countPalubes && shipy == 0)
					{
						if(mas[shipx - 1][shipy + 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
												
						if(mas[shipx + countPalubes - 1][shipy + 1] =='0')
						if(mas[shipx + countPalubes - 1][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy + 1] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
								
							}
						}	
					}
					if(shipx == 0 && shipy == 0)
					{
						if(mas[shipx + countPalubes][shipy + 1] =='0')
						if(mas[shipx + countPalubes][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy + 1] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
								
							}
						}	
					}
					if(shipx > 0 && shipx < mas.length - countPalubes && shipy == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
															
						if(mas[shipx + countPalubes][shipy - 1] =='0')
						if(mas[shipx + countPalubes][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy - 1] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
								
							}
						}	
					}if(shipx > 0 && shipx < mas.length - countPalubes && shipy == 0)
					{
						if(mas[shipx - 1][shipy + 1] =='0')
						if(mas[shipx - 1][shipy] =='0')
															
						if(mas[shipx + countPalubes][shipy + 1] =='0')
						if(mas[shipx + countPalubes][shipy] =='0')
						{	
							int k = 0;	
							for(int x = shipx; x < shipx + countPalubes; x++)
							{
								if(mas[x][shipy] == '0' && mas[x][shipy + 1] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int x = shipx; x < shipx + countPalubes; x++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[x][shipy] =  ss.charAt(0);
								}
								
							}
						}	
					}
					
				}
			}	
			else
			{
				int shipx = rand.nextInt(mas.length );
				int shipy = rand.nextInt(mas.length - (countPalubes - 1));
			
				if(mas[shipx][shipy] == '0')
				{
				
					if(shipx > 0 && shipy > 0 && 
							shipx < mas.length - 1  && shipy < mas.length - countPalubes)
						{
							if(mas[shipx - 1][shipy - 1] =='0')
							if(mas[shipx][shipy - 1] =='0')
							if(mas[shipx + 1][shipy - 1] =='0')
						
							if(mas[shipx - 1][shipy + countPalubes] =='0')
							if(mas[shipx][shipy + countPalubes] =='0')
							if(mas[shipx + 1][shipy + countPalubes] =='0')
							{	
								int k = 0;	
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0' && mas[shipx + 1][y] == '0') k++;
								}
								if(k == countPalubes) 
								{
									 countShips++;
									for(int y = shipy; y < shipy + countPalubes; y++)
									{
										String ss = "";
										ss = Integer.toString(countPalubes);
										mas[shipx][y] =  ss.charAt(0);
									}
								}
							}	
						}
					if(shipy == 0 && shipx > 0 && shipx < mas.length - 1)
						{
					
							if(mas[shipx - 1][shipy + countPalubes] =='0')
							if(mas[shipx][shipy + countPalubes] =='0')
							if(mas[shipx + 1][shipy + countPalubes] =='0')
							{	
								int k = 0;	
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0' 
									&& mas[shipx + 1][y] == '0') k++;
								}
								if(k == countPalubes) 
								{
									 countShips++;
									for(int y = shipy; y < shipy + countPalubes; y++)
									{
										String ss = "";
										ss = Integer.toString(countPalubes);
										mas[shipx][y] = ss.charAt(0);
									}
								}
							}	
						}
					
					if(shipy == mas.length - countPalubes && shipx > 0 && shipx < mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
						if(mas[shipx + 1][shipy - 1] =='0')
						
						if(mas[shipx - 1][shipy + countPalubes - 1] =='0')
						if(mas[shipx][shipy + countPalubes - 1] =='0')
						if(mas[shipx + 1][shipy + countPalubes - 1] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0' 
								&& mas[shipx + 1][y] == '0') k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
								}
							}
						}	
					}
				
					if(shipy == mas.length - countPalubes && shipx == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
												
						if(mas[shipx - 1][shipy + countPalubes - 1] =='0')
						if(mas[shipx][shipy + countPalubes - 1] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
								}
							}
						}	
					}
					
					
					if(shipy == 0 && shipx == mas.length - 1)
					{
						if(mas[shipx - 1][shipy + countPalubes] =='0')
						if(mas[shipx][shipy + countPalubes] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
									}
							}
						}	
					}
					if(shipy == mas.length - countPalubes && shipx == 0)
					{
						if(mas[shipx + 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
												
						if(mas[shipx + 1][shipy + countPalubes - 1] =='0')
						if(mas[shipx][shipy + countPalubes - 1] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx + 1][y] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
								}
								
							}
						}	
					}
					
					if(shipx == 0 && shipy == 0)
					{
						if(mas[shipx + 1][shipy + countPalubes] =='0')
						if(mas[shipx][shipy + countPalubes] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx + 1][y] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
								}
							}
						}	
					}
					
					if(shipy > 0 && shipy < mas.length - countPalubes && shipx == mas.length - 1)
					{
						if(mas[shipx - 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
															
						if(mas[shipx - 1][shipy + countPalubes] =='0')
						if(mas[shipx][shipy + countPalubes] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx - 1][y] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
								}
							}
						}	
					}
					
				
					if(shipy > 0 && shipy < mas.length - countPalubes && shipx == 0)
					{
						if(mas[shipx + 1][shipy - 1] =='0')
						if(mas[shipx][shipy - 1] =='0')
															
						if(mas[shipx + 1][shipy + countPalubes] =='0')
						if(mas[shipx][shipy + countPalubes] =='0')
						{	
							int k = 0;	
							for(int y = shipy; y < shipy + countPalubes; y++)
							{
								if(mas[shipx][y] == '0' && mas[shipx + 1][y] == '0'
										) k++;
							}
							if(k == countPalubes) 
							{
								 countShips++;
								for(int y = shipy; y < shipy + countPalubes; y++)
								{
									String ss = "";
									ss = Integer.toString(countPalubes);
									mas[shipx][y] = ss.charAt(0);
								}
							}
						}	
					}
					
				}
			}	
		}
		if(countGeneration > 2000) System.out.println("countGeneration = " + countGeneration);
	}
	
}
