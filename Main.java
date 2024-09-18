package com.dam.rafa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String directorio;
        int menu = 0;
        String extension = "";
        String archivo = "";


        System.out.println("Introduce la ruta un directorio");
        directorio = teclado.nextLine();

        System.out.println("Introduce de que actividad quieres ver el resultado. Van del 1 al 8");
        System.out.println("1 - Realitza un programa que reba com a paràmetre d’entrada un directori i el " +
                "mostre per pantalla.");
        System.out.println("2 - Ampliar el programa anterior per tal que mostre totes les característiques " +
                "d’interés del directori, prenent com a referència la informació que proporciona la classe File.");
        System.out.println("3 - Introduir una comprobació en el programa anterior per determinar si el " +
                "directori existeix.");
        System.out.println("4 - Realitzar un programa que donat un directori, comprove si existeix i torne un " +
                "missatge de confirmació si existeix o una alerta en cas contrari");
        System.out.println("5 - Realitza un programa que reba com a paràmetres d’entrada un directori i una extensió " +
                "de fitxer (por exemple .txt) i torne per pantalla tots els fitxers del directori que " +
                "complisquen el criteri.");
        System.out.println("6 - Modifica el programa anterior per que tinga en compte que si no se li passa " +
                "ninguna extensió com a paràmetre, mostre tot el contingut del directori.");
        System.out.println("7 - Modifica el programa anterior per tal que admeta com a paràmetres d’entrada un " +
                "nombre qualsevol d’extensions, tornant després per pantalla tots els fitxers del directori que " +
                "tinguen alguna de les extensions indicades");
        System.out.println("8 - Desenvolupa un programa que donat un fitxer, realitze una còpia del mateix " +
                "(en el mateix directori i canviant-li el nom) i el borre després. Mostra una traça per pantalla" +
                " de les accions per a veure que es realitzen.");

        menu = teclado.nextInt();

        File dir = new File(directorio);
        Main main = new Main();

        switch (menu) {

            case 1:
                main.act1(dir);
                break;

            case 2:
                main.act2(dir);
                break;

            case 3:
                main.act3(dir);
                break;

            case 4:
                main.act4(dir);
                break;

            case 5:
                System.out.println("Introduce la extension del archivo");
                teclado.nextLine();
                extension = teclado.nextLine();

                main.act5(dir,extension);
                break;

            case 6:
                System.out.println("Introduce la extension del archivo");
                teclado.nextLine();
                extension = teclado.nextLine();

                main.act6(dir,extension);

                break;

            case 7:
                ArrayList<String> arrayExtensiones = new ArrayList<>();
                boolean menu2 = true;
                teclado.nextLine();
                while (menu2) {
                    System.out.println("Introduce las extensiones de archivosque quieras una por una" +
                            " y pulsa 0 cuando ya lo tengas");

                    extension = teclado.nextLine();
                    if (extension.equals("0")) {
                        menu2 = false;
                    } else {
                        arrayExtensiones.add(extension);
                    }

                }
                main.act7(dir,arrayExtensiones);

                break;

            case 8:
                System.out.println("Introduce el nombre de un archivo");
                teclado.nextLine();
                archivo = teclado.nextLine();
                main.act8(dir,archivo);
                break;


        }

    }

    public void act1(File directorio) {
        System.out.println("El directorio introducido es: "+directorio);
    }

    public void act2 (File directorio) {
        System.out.println("Ruta introducida por el usuario--> " + directorio);
        System.out.println("Nombre de la carpeta [.getName] --> "+directorio.getName());
        System.out.println("Ruta relativa [.getPath()] --> "+directorio.getPath());
        System.out.println("Ruta absoluta [.getAbsolutePath()] --> "+directorio.getAbsolutePath());
        System.out.println("Se puede ejecutar? [.canExecute] --> "+directorio.canExecute());
        System.out.println("Se puede leer? [.canRead] --> "+directorio.canRead());
        System.out.println("Se puede escrbir? [.canWrite] --> "+directorio.canWrite());
        System.out.println("El archivo/carpeta existe? [.exists] --> "+directorio.exists());
        System.out.println("El archivo/carpeta es un archivo? [.isFile] --> "+directorio.isFile());
        System.out.println("El archivo/carpeta es una carpeta? [.isFile] --> "+directorio.isDirectory());

    }

    public void act3 (File directorio) {
        System.out.println(directorio.exists());
        System.out.println("El comando es el siguiente: directorio.exists()");

    }

    public void act4 (File directorio) {

        if (directorio.exists()) {
            System.out.println("El directorio existe");
        }else {
            System.out.println("El directorio no existe");
        }

    }

    public void act5 (File directorio, String extension) {

        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(extension));
            if (archivos != null && archivos.length > 0) {
                System.out.println("Los archvos con la extension (" + extension + ") son:");
                for (File archivo : archivos) {
                    System.out.println(archivo.getName());
                }
            } else {
                System.out.println("No se encontraron archivos con la extension " + extension);
            }
        } else {
            System.out.println("El directorio proporcionado no es valido.");
        }

    }

    public void act6 (File directorio, String extension) {
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles((dir, name)
                    -> name.endsWith(extension));
            if (archivos != null && archivos.length > 0 && extension != null && extension.isEmpty()==false) {
                    for (File archivo : archivos) {
                        System.out.println(archivo.getName());
                    }

            } else {

                if (extension == null || extension.isEmpty()) {
                    System.out.println("Como no has introducido extension a continuacion te mesutro todos " +
                            "los ficheros");
                    } else {
                    System.out.println("No se encontraron archivos con la extension " + extension + "Los archivos" +
                            " existentes son:");
                }

                for (File archivo : archivos) {
                    System.out.println(archivo.getName());
                }
            }
        } else {
            System.out.println("El directorio proporcionado no es valido.");
        }

    }

    public void act7 (File directorio, ArrayList<String> arrayExtensiones) {
        String extension = "";
        if (directorio.isDirectory()) {
            for (String s : arrayExtensiones) {
                extension = s;
                String finalExtension = extension;
                File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(finalExtension));
                if (archivos != null && archivos.length > 0 && extension != null && extension.isEmpty() == false) {
                    for (File archivo : archivos) {
                        System.out.println(archivo.getName());
                    }

                } else {

                    if (extension == null || extension.isEmpty()) {
                        System.out.println("Como no has introducido extension a continuacion te mesutro todos " +
                                "los ficheros");
                    } else {
                        System.out.println("No se encontraron archivos con la extension " + extension + "Los archivos" +
                                " existentes son:");
                    }

                    for (File archivo : archivos) {
                        System.out.println(archivo.getName());
                    }
                }
            }
        } else {
            System.out.println("El directorio proporcionado no es valido.");
        }

    }

    public void act8 (File directorio, String archivo) {

        try {
            File originalFile = new File(directorio, archivo);
            if (originalFile.exists()) {
                String nuevoNombre = "nuevo_" + archivo;
                File nuevoArchivo = new File(directorio, nuevoNombre);

                Path origen = originalFile.toPath();
                Path destino = nuevoArchivo.toPath();
                StandardCopyOption opcion = StandardCopyOption.REPLACE_EXISTING;
                Files.copy(origen, destino, opcion);
                System.out.println("Archivo copiado y renombrado a: " + nuevoNombre);

                if (nuevoArchivo.delete()) {
                    System.out.println("El nuevo archivo ha sido borrado");
                } else {
                    System.out.println("No se pudo borrar el nuevo archivo");
                }
            } else {
                System.out.println("El archivo no existe.");
            }
        } catch (IOException e) {}

    }
    }