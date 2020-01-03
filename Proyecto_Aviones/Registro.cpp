#include <iostream>
#include <string>
#include "Registro.h"
#include <conio.h>
#include <stdlib.h>
using namespace std;
using std::string;

Registro::Registro()
{
	dia = 0;
	horaSalida = 0;
	asientoVP = 0;
	asientoNumero = 0;

	for (int i = 0; i < 20; i++)
	{
		avion1[i] = 0;
		avion2[i] = 0;
		avion3[i] = 0;
		avion4[i] = 0;
		avion5[i] = 0;
	}
}


void Registro::imprimir()
{
	char opcion;


	do
	{
		system("cls");

		cout << "\t|==  MENU  ==|\n" << endl
			<< " -1- \tHorario de vuelos" << endl
			<< " -2- \tReservar " << endl
			<< " -3- \tConfirmar reserva" << endl
			<< " -4- \tCancelar reserva" << endl
			<< " -5- \tDisponibilidad de vuelos" << endl
			<< " -6- \tSalir del menu " << endl
			<< " \nIngrese la opcion deseada: ";
		cin >> opcion;
		switch (opcion)
		{
		case '1':
			system("cls");
			cout << "Disponible los Dias Lunes, Martes y Jueves" << endl;
			cout << "\nAviones:\n-1-\tAirbus-A-300\n-2-\tAirbus-A-380\n-3-\tBoeing-707\n-4-\tBoeing-757\n-5-\tCessna-A-37B" << endl << endl;

			cout << "||================(Quito-Miami)=================||" << endl
				<< "\nAirbus-A-300:\tHora de salida 6:00am" << endl
				<< "\nAirbus-A-380:\tHora de salida 7:00am" << endl
				<< "\nBoeing-707  :\tHora de salida 8:00am" << endl
				<< "\nBoeing-757  :\tHora de salida 9:00am" << endl
				<< "\nCessna-A-37B:\tHora de salida 10:00am" << endl;
			cout << endl; system("PAUSE");

			break;

		case '2':
			system("cls");
			cout << "\nSeleccione el avion que desea abordar:\n -1-\tAirbus-A-300\t(6:00am)\n -2-\tAirbus-A-380\t(7:00am)\n -3-\tBoeing-707 \t(8:00am)\n -4-\tBoeing-757 \t(9:00am)\n -5-\tCessna-A-37B\t(10:00am)\nSu Opcion Es: ";
			cin >> horaSalida;
			cout << "\nSeleccione dia:\n -1-\tLunes\n -2-\tMartes\n -3-\tJueves\nSu opcion es: ";
			cin >> dia;
			cout << "\nQue lugar desea tomar?:\n -1-\tVentana\n -2-\tPasillo\nSu opcion es:";
			cin >> asientoVP;
			if (asientoVP == 1)
			{
				cout << "\nAsientos Disponibles entre 1 - 10: ";
				do {
					cout << "\nDigite el numero del asiento deseado: ";
					cin >> asientoNumero;
					if ((asientoNumero < 1) || (asientoNumero>10))
						cout << "\n**Este Asiento No es valido**";
				} while ((asientoNumero < 1) || (asientoNumero>10));
			}
			else if (asientoVP == 2)
			{
				cout << "\nAsientos Disponibles entre 11 - 20:";
				do {
					cout << "\nDigite el numero del asiento deseado: ";
					cin >> asientoNumero;
					if ((asientoNumero < 11) || (asientoNumero>20))
						cout << "\n**Este Asiento No es valido**";
				} while ((asientoNumero < 11) || (asientoNumero>20));
			}
			if (horaSalida == 1)
			{
				if (avion1[asientoNumero] == 0)
				{
					avion1[asientoNumero] = 1;
					cout << "\nReserva Exitosa (^_^)";
				}
				else
					cout << "\nEste Asiento No Esta Disponible";
			}
			if (horaSalida == 2)
			{
				if (avion2[horaSalida == 2] == 0)
				{
					avion2[asientoNumero] = 1;
					cout << "\nReserva Exitosa (^_^)";
				}
				else
					cout << "Este Asiento No Esta Disponible";
			}
			if (horaSalida == 3)
			{
				if (avion3[horaSalida == 3] == 0)
				{
					avion3[asientoNumero] = 1;
					cout << "\nReserva Exitosa (^_^)";
				}
				else
					cout << "Este Asiento No Esta Disponible";
			}
			if (horaSalida == 4)
			{
				if (avion4[horaSalida == 4] == 0)
				{
					avion4[asientoNumero] = 1;
					cout << "\nReserva Exitosa (^_^)";
				}
				else
					cout << "El Asiento No Esta Disponible";
			}
			if (horaSalida == 5)
			{
				if (avion5[horaSalida == 5] == 0)
				{
					avion5[asientoNumero] = 1;
					cout << "\nReserva Exitosa (^_^)";
				}
				else
					cout << "Este Asiento No Esta Disponible....";
			}
			cout << endl; system("PAUSE");
			break;

		case '3':
			system("cls");
			cout << "\nConfirmar su reserva:""\n""Ingrese la opcion del avion que reservo:";
			cout << "||================(Quito-Miami)=================||" << endl
				<< "\nAirbus-A-300:\tHora de salida 6:00am" << endl
				<< "\nAirbus-A-380:\tHora de salida 7:00am" << endl
				<< "\nBoeing-707  :\tHora de salida 8:00am" << endl
				<< "\nBoeing-757  :\tHora de salida 9:00am" << endl
				<< "\nCessna-A-37B:\tHora de salida 10:00am" << endl;
			cout << "Opcion: ";
			cin >> horaSalida;
			cout << "\nIngrese el numero del asiento:";
			cin >> asientoNumero;
			if (horaSalida == 1)
			{
				if (avion1[asientoNumero] == 1)
				{
					avion1[asientoNumero] = 2;
					cout << "Confirmacion Exitosa(^_^)";
					if (asientoNumero < 10)
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " VENTANA -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo partira a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
					else
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " PASILLO -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo Partira de Quito a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
				}
				else
					cout << "El asiento ingresado no ha sido reservado aun...";
			}
			if (horaSalida == 2)
			{
				if (avion2[asientoNumero] == 1)
				{
					avion2[asientoNumero] = 2;
					cout << "Confirmacion Exitosa(^_^)";
					if (asientoNumero < 10)
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " VENTANA -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo partira a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
					else
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " PASILLO -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo Partira de Quito a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
				}
				else
					cout << "El asiento ingresado no ha sido reservado aun...";
			}
			if (horaSalida == 3)
			{
				if (avion3[asientoNumero] == 1)
				{
					avion3[asientoNumero] = 2;
					cout << "Confirmacion Exitosa(^_^)";
					if (asientoNumero < 10)
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " VENTANA -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo partira a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
					else
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " PASILLO -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo Partira de Quito a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
				}
				else
					cout << "El asiento ingresado no ha sido reservado aun...";
			}
			if (horaSalida == 4)
			{
				if (avion4[asientoNumero] == 1)
				{
					avion4[asientoNumero] = 2;
					cout << "Confirmacion Exitosa(^_^)";
					if (asientoNumero < 10)
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " VENTANA -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo partira a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
					else
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " PASILLO -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo Partira de Quito a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
				}
				else
					cout << "El asiento ingresado no ha sido reservado aun...";
			}
			if (horaSalida == 5)
			{
				if (avion5[asientoNumero] == 1)
				{
					avion5[asientoNumero] = 2;
					cout << "Confirmacion Exitosa(^_^)";
					if (asientoNumero < 10)
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " VENTANA -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo partira a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
					else
					{
						cout << "\nUsted reservo:\nAsiento: " << asientoNumero << " PASILLO -> Dia: ";
						if (dia == 1){ cout << "Lunes"; }
						if (dia == 2){ cout << "Martes"; }
						if (dia == 3){ cout << "Jueves"; }
						cout << "\nSu vuelo Partira de Quito a las: ";
						if (horaSalida == 1){ cout << "6:00am"; }
						if (horaSalida == 2){ cout << "7:00am"; }
						if (horaSalida == 3){ cout << "8:00am"; }
						if (horaSalida == 4){ cout << "9:00am"; }
						if (horaSalida == 5){ cout << "10:00am"; }
					}
				}
				else
					cout << "El asiento ingresado no ha sido reservado aun...";
			}
			cout << endl; system("PAUSE");
			break;
		case '4':
			system("cls");			
			cout << "\nCancelar su reserva:""\n""Ingrese la opcion del avion que reservo:";
			cout << "||================(Quito-Miami)=================||" << endl
				<< "\nAirbus-A-300:\tHora de salida 6:00am" << endl
				<< "\nAirbus-A-380:\tHora de salida 7:00am" << endl
				<< "\nBoeing-707  :\tHora de salida 8:00am" << endl
				<< "\nBoeing-757  :\tHora de salida 9:00am" << endl
				<< "\nCessna-A-37B:\tHora de salida 10:00am" << endl;
			cout << "Opcion: ";
			cin >> horaSalida;
			cout << "\nIngrese el numero de Asiento: ";
			cin >> asientoNumero;
			if (horaSalida == 1)
			{
				if (avion1[asientoNumero] == 0)
					cout << "El asiento ingresado no ha sido reservado aun...";
				{if (avion1[asientoNumero] == 2)
					cout << "El Asiento no puede ser cancelado. \n**Gracias**";
				else
				{
					if (avion1[asientoNumero] == 1)
						avion1[asientoNumero] = 0;
					cout << "\nSu Reserva ha sido cancelada";
				}
				}
			}
			if (horaSalida == 2)
			{
				if (avion2[asientoNumero] == 0)
					cout << "El asiento ingresado no ha sido reservado aun...";
				{if (avion2[asientoNumero] == 2)
					cout << "El Asiento no puede ser cancelado. \n**Gracias**";
				else
				{
					if (avion2[asientoNumero] == 1)
						avion2[asientoNumero] = 0;
					cout << "\nSu Reserva ha sido cancelada";
				}
				}
			}
			if (horaSalida == 3)
			{
				if (avion3[asientoNumero] == 0)
					cout << "El asiento ingresado no ha sido reservado aun...";
				{if (avion3[asientoNumero] == 2)
					cout << "El Asiento no puede ser cancelado. \n**Gracias**";
				else
				{
					if (avion3[asientoNumero] == 1)
						avion3[asientoNumero] = 0;
					cout << "\nSu Reserva ha sido cancelada";
				}
				}
			}
			if (horaSalida == 4)
			{
				if (avion4[asientoNumero] == 0)
					cout << "El asiento ingresado no ha sido reservado aun...";
				{if (avion4[asientoNumero] == 2)
					cout << "El Asiento no puede ser cancelado. \n**Gracias**";
				else
				{
					if (avion4[asientoNumero] == 1)
						avion4[asientoNumero] = 0;
					cout << "\nSu Reserva ha sido cancelada";
				}
				}
			}
			if (horaSalida == 5)
			{
				if (avion5[asientoNumero] == 0)
					cout << "El asiento ingresado no ha sido reservado aun...";
				{if (avion5[asientoNumero] == 2)
					cout << "El Asiento no puede ser cancelado. \n**Gracias**";
				else
				{
					if (avion5[asientoNumero] == 1)
						avion5[asientoNumero] = 0;
					cout << "\nSu Reserva ha sido cancelada";
				}
				}
			}
			cout << endl; system("PAUSE");
			break;
		case '5':
			system("cls");
			cout << "\nQue avion verificara\n -1-\tAirbus-A-300 -> 6:00 am\n -2-\tAirbus-A-380 -> 7:00 am\n -3-\tBoeing-707 -> 8:00 am\n -4-\tBoeing-757 -> 9:00 am\n -5-\tCessna-A-37B -> 10:00 am\nOpcion: \n";
			cin >> horaSalida;
			if (horaSalida == 1)
			{
				cout << "Asientos Reservados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion1[i] == 1)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Confirmados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion1[i] == 2)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Vacios: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion1[i] == 0)
						cout << i << "-";
				}
			}
			if (horaSalida == 2)
			{
				cout << "Asientos Reservados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion2[i] == 1)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Confirmados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion2[i] == 2)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Vacios: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion2[i] == 0)
						cout << i << "-";
				}
			}

			if (horaSalida == 3)
			{
				cout << "Asientos Reservados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion3[i] == 1)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Confirmados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion3[i] == 2)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Vacios: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion3[i] == 0)
						cout << i << "-";
				}
			}
			if (horaSalida == 4)
			{
				cout << "Asientos Reservados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion4[i] == 1)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Confirmados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion4[i] == 2)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Vacios: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion4[i] == 0)
						cout << i << "-";
				}
			}
			if (horaSalida == 5)
			{
				cout << "Asientos Reservados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion5[i] == 1)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Confirmados: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion5[i] == 2)
						cout << i << "-";
				}
				cout << endl;
				cout << "Asientos Vacios: \n";
				for (int i = 0; i <= 20; i++)
				{
					if (avion5[i] == 0)
						cout << i << "-";
				}
			}
			cout << endl; system("PAUSE");
			break;
		case '6':
			system("cls");
			cout << "\n**GRACIAS POR PREFERIRNOS**" << endl;
			cout << endl;
			break;
		default:
			system("cls");
			cout << "\n*Dato ingresado incorrecto*\nVuelva a ingresar" << endl;
			system("PAUSE");
			break;
		}
	} while (opcion != '6');
}

