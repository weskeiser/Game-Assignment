import Hero.Hero;

public class App {
    public static void main(String[] args) throws Exception {
        Hero bernt = new Hero.HeroBuilder("Bernt").setLevelAttributes(true).build();

        System.out.println(bernt.getName());
        System.out.println(bernt.getLevelAttributes());

    }
}
