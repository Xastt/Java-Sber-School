package ru.xast.sbertasks.task2.SecondTask;

public class MainPhone {
    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber();

        phoneNumber.add("Павлов", "88005553535");
        phoneNumber.add("Смирнов", "88006663636");
        phoneNumber.add("Петров", "88007773737");
        phoneNumber.add("Павлов", "88008883838");
        phoneNumber.add("Смирнов", "88009993939");
        phoneNumber.add("Иванов", "88004443434");

        System.out.println("-------------ПОЛУЧЕНИЕ НОМЕРОВ-------------");

        System.out.println(phoneNumber.get("Павлов"));
        System.out.println(phoneNumber.get("Смирнов"));
        System.out.println(phoneNumber.get("Петров"));
        System.out.println(phoneNumber.get("Иванов"));

        System.out.println("----------НЕСУЩЕСТВУЮЩАЯ ФАМИЛИЯ----------");

        System.out.println(phoneNumber.get("Борисов"));

        System.out.println("----------УЖЕ СУЩЕСТВУЮЩИЙ НОМЕР-----------");

        phoneNumber.add("Павлов", "88005553535");

    }
}
