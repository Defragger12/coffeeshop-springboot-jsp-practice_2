package com.scand.coffeeshopboot;

import com.scand.coffeeshopboot.domain.Coffee;
import com.scand.coffeeshopboot.repository.CoffeeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class CoffeeshopbootApplication {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {

        SpringApplication.run(CoffeeshopbootApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {

            List<Coffee> existingCoffees = coffeeRepository.findAll();
            if (existingCoffees.size() < 9) {
                coffeeRepository.deleteAll();

                coffeeRepository.save(
                        new Coffee("Cappuccino", "A cappuccino is a coffee-based drink made primarily from espresso and milk. It consists of one-third espresso, one-third heated milk and one-third milk foam and is generally served in a 6 to 8-ounce cup. The cappuccino is considered one of the original espresso drinks representative of Italian espresso cuisine and eventually Italian-American espresso cuisine.\n" +
                                "\n" +
                                "According to legend, the word cappuccino comes from the Capuchin monastic order. In 1683, soldiers fighting for Marco d'Aviano, a monk from the order, found a hoard of coffee following a victory over the Ottomans in the Battle of Vienna. The coffee was too strong for the European's tastes, so they diluted it with cream and honey. The resulting brown beverage matched the robes of the monk, and was dubbed cappuccino after the order.", "../img/coffee/cappuccino.jpg" ,new BigDecimal(1234))
                );
                coffeeRepository.save(
                        new Coffee("Americano", "Americano (Italian: American coffee) is a style of coffee prepared by adding hot water to espresso, giving a similar strength but different flavor from regular drip coffee. The strength of an Americano varies with the number of shots of espresso and the amount of water added.\n" +
                                "In the United States, \"Americano\" is used broadly to mean combining hot water and espresso in either order, but in a narrower definition it refers to adding water to espresso (espresso on the bottom), while adding espresso to water (espresso on the top) is instead referred to as a long black.\n" +
                                "The name is also spelled with varying capitalization and use of diacritics: e.g. Café Américano – a hyperforeignism using the French word for coffee and the Italian word for American, but with an additional incorrect accent - café Americano, cafe americano, etc.", "../img/coffee/americano.jpg" ,new BigDecimal(4321))
                );
                coffeeRepository.save(
                        new Coffee("Espresso", "Espresso (ess-PRESS-oh) is a full-flavored, concentrated form of coffee that is served in “shots.” It is made by forcing pressurized, hot water through very finely ground coffee beans. This process is called “pulling a shot.”", "../img/coffee/espresso.jpg" ,new BigDecimal(111))
                );
                coffeeRepository.save(
                        new Coffee("Macchiato", "The macchiato (mah-key-AH-toe) is a cornerstone of Italian coffee culture, along with the espresso and cappuccino, among other coffee drinks. It's basically an espresso (served in a demitasse cup) with a small amount of foamed milk on top -- the name macchiato means \"marked.\" So really, you could look at it as a cross between an espresso and a cappuccino. Since Italians only drink cappuccino in the morning, a macchiato gives the afternoon drinker the option of having a little milk in their espresso for some extra flavor. It's also a good option for those who can't tolerate a strong espresso but find a cappuccino too weak and milky.\n" +
                                "\n" +
                                "There is another type of macchiato called \"latte macchiato,\" which is a cup of hot milk with a shot of espresso, but it does not have caramel-flavored syrup added, which is what the chain coffee shop would have you believe is a true macchiato.", "../img/coffee/macchiato.png" ,new BigDecimal(12))
                );
                coffeeRepository.save(
                        new Coffee("Mocha", "Caffè Mocha or café mocha, is an American invention and a variant of a caffe latte, inspired by the Turin coffee beverage Bicerin. The term \"caffe mocha\" is not used in Italy nor in France, where it is referred to as a \"mocha latte\". Like a caffe latte, it is typically one third espresso and two thirds steamed milk, but a portion of chocolate is added, typically in the form of sweet cocoa powder, although many varieties use chocolate syrup. Mochas can contain dark or milk chocolate.\n" +
                                "\n" +
                                "Like cappuccino, café mochas contain the well-known milk froth on top, although they are sometimes served with whipped cream instead. They are usually topped with a dusting of either cinnamon or cocoa powder. Marshmallows may also be ded on top for flavor and decoration.", "../img/coffee/mocha.jpg", new BigDecimal(999))
                );
                coffeeRepository.save(
                        new Coffee("Coffee Latte", "Caffè latte is a coffee-based drink made primarily from espresso and steamed milk. It consists of one-third espresso, two-thirds heated milk and about 1cm of foam. Depending on the skill of the barista, the foam can be poured in such a way to create a picture. Common pictures that appear in lattes are love hearts and ferns. Latte art is an interesting topic in itself.\n" +
                                "\n" +
                                "Traditionally the cafe latte is a ratio of two parts coffee and one part steamed milk (also called a Café Au Lait).", "../img/coffee/coffee-latte.jpg" ,new BigDecimal(1000))
                );
                coffeeRepository.save(
                        new Coffee("Piccolo Latte", "The drink, as we know it, became popular in the Sydney coffee scene about ten years ago among the coffee roasting fraternity and soon spread its wings around the country. Only the cool crowd drank piccolos, and the coffee bores annoyed many a café barista about the true definition of this drink. I remember responding to an early coffee forum years ago and was eaten alive by know it all coffee aficionados.", "../img/coffee/piccolo-latte.png" ,new BigDecimal(5))
                );
                coffeeRepository.save(
                        new Coffee("Ristretto", "Ristretto means “restricted” in Italian. In the context of coffee, ristretto refers to a short (restricted) shot of espresso, meaning the barista pulls only the first portion of a full-length espresso shot. ", "../img/coffee/ristretto.jpg" ,new BigDecimal(9999))
                );
                coffeeRepository.save(
                        new Coffee("Affogato", "This. This right here is enough to make me say \"do I really want a summer body?\" This is heaven in a glass. This, ladies and gents, is an affogato. Roughly translated as the nectar of the gods, baby angel tears, or a kiss from Cupid himself. It is a shot of espresso poured ever so gracefully over a scoop of gelato, made to be eaten with a spoon until one can no longer handle it and decides to gulp down the melted heaven left in the glass.\n" +
                                "\n" +
                                "This particular affogato is from Piccione's in the Delmar loop. It is made with vanilla gelato, rich creamy espresso, thick chocolate syrup lining the bottom, freshly made and piped whipped cream, and adorable chocolate shavings on top. The barista was nice enough to give me an extra shot which I happily poured into the remaining affogato soup towards the end of my ascent into heaven. And of course, a nice crunchy almond biscotti on the side", "../img/coffee/affogato.jpg" ,new BigDecimal(49))
                );
            }
        };
    }
}
