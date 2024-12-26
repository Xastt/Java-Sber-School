package ru.xast.sbertasks.task12.utils;

import ru.xast.sbertasks.task12.models.SkillOut;

import java.util.List;

public class SkillPrinter {
    public static void printSkills(List<SkillOut> skillOutList) {
        if (skillOutList == null || skillOutList.isEmpty()) {
            System.out.println("Список профилей пуст.");
            return;
        }
        for (SkillOut skillOut : skillOutList) {
            System.out.println("ID: " + skillOut.getId());
            System.out.println("Фамилия: " + skillOut.getSurname());
            System.out.println("Имя: " + skillOut.getName());
            System.out.println("Номер телефона: " + skillOut.getPhoneNumber());
            System.out.println("Электронная почта: " + skillOut.getEmail());
            System.out.println("Навык: " + skillOut.getSkillName());
            System.out.println("Описание навыка: " + skillOut.getSkillDescription());
            System.out.println("Стоимость: " + skillOut.getCost());
            System.out.println("Описание пользователя: " + skillOut.getPersDescription());
            System.out.println("Опыт: " + skillOut.getExp());
            System.out.println("Рейтинг: " + skillOut.getRating());
            System.out.println("-----------------------------");
        }
    }
}
