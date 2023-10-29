import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Observer Interface
interface Observer {
    void update(String heroName);
}

// Concrete Observer: HeroSelector
class HeroSelector implements Observer {
    @Override
    public void update(String heroName) {
        System.out.println("You've selected " + heroName);
    }
}

// Subject: Hero
abstract class Hero {
    protected String name;
    protected List<Observer> observers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.name);
        }
    }
}

// Concrete Subjects: Warrior, Mage, Archer, Healer
class Warrior extends Hero {
    public Warrior(String name) {
        this.name = name;
    }
}

class Mage extends Hero {
    public Mage(String name) {
        this.name = name;
    }
}

class Archer extends Hero {
    public Archer(String name) {
        this.name = name;
    }
}

class Healer extends Hero {
    public Healer(String name) {
        this.name = name;
    }
}

// Factory: HeroFactory
class HeroFactory {
    public static Hero createHero(String type, String name) {
        switch (type) {
            case "Warrior":
                return new Warrior(name);
            case "Mage":
                return new Mage(name);
            case "Archer":
                return new Archer(name);
            case "Healer":
                return new Healer(name);
            default:
                throw new IllegalArgumentException("Invalid hero type: " + type);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HeroSelector heroSelector = new HeroSelector();

        System.out.println("Choose your hero:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Archer");
        System.out.println("4. Healer");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        String[] heroTypes = {"Warrior", "Mage", "Archer", "Healer"};
        if (choice >= 1 && choice <= 4) {
            String selectedHeroType = heroTypes[choice - 1];
            String heroName = selectedHeroType + "1"; // You can ask for a name here

            Hero selectedHero = HeroFactory.createHero(selectedHeroType, heroName);
            selectedHero.addObserver(heroSelector);
            selectedHero.notifyObservers();
        } else {
            System.out.println("Invalid choice. Please enter a number from 1 to 4.");
        }
    }
}
