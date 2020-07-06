package com.github.dhiraj072.randomwordgenerator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.dhiraj072.randomwordgenerator.datamuse.DataMuseRequest;
import com.github.dhiraj072.randomwordgenerator.exceptions.DataMuseException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Integration tests with <a href="https://api.datamuse.com/words">DataMuse API endpoint</a>
 */
class RandomWordGeneratorIntegrationTest {

  @Test
  void testGetsRandomWordCorrectly() {

    String word = RandomWordGenerator.getRandomWord();
    assertNotNull(word);
  }

  @Test
  void testGetsRandomWordByTopicCorrectly() throws DataMuseException {

    DataMuseRequest request = new DataMuseRequest().topics("Car");
    String actualWord = RandomWordGenerator.getRandomWord(request);
    // Expected list of words we should get as per a manual request for https://api.datamuse.com/words?topics=Car
    // executed on DataMuse API on 4 July 2020. There is a little chance 'might' change in future which will
    // break this test, and should be updated accordingly
    List<String> expectedWords = Arrays.asList("automobile","motorcar","auto","railcar","machine","gondola","cable car","elevator car","railroad car","railway car","vehicle","truck","sedan","van","suv","vehicles","driver","cruiser","cab","tire","motor","limo","passenger","taxi","bus","wheel","trunk","hood","lorry","mercedes","buick","automobiles","driving","boat","engine","bodywork","motoring","automotive","trolley","wagon","wreck","escalade","chariot","carmaker","cart","backseat","cadillac","train","automaker","motors","autos","carload","cabin","road","carpark","drive","accident","parking","valet","vehicular","crate","boxcar","beater","malibu","carriage","body","tank","compartment","caboose","traffic","ride","bomb","wagons","glove","machinery","cashier","consignment","pull","warrant","cash","bombs","conveyance","carla","mine","suicide","unit","box","because","jaguar","pinto","nativity","shoot","hop","acr","coach","fund","cln","float","bandwagon","lading","calorimeter","vault","checkout","rac","cal","caisse","fiat","indictment","blueberry","crd","regt","carl","self","laurie","twisted","cha","since","marty","cals","writ","cue","rca","aut","che","kar","order","booby-trapped","drive-by","karr","kars","motor-vehicle","motormen","pmv","racs","railcars","rar","rtas","self-","stackhouse","char","drc","rda","minivan","motorcycle","motorbike","jeep","garage","racecar","camry","coupe","lamborghini","roadster","volvo","motorist","dealership","pickup truck","scooter","bike","station wagon","pace car","moped","hatchback","tractor","motor scooter","oldsmobile","sport utility","wheeler","bicycle","autoist","lawnmower","kart","vetturino","ute","maserati","sportscar","steering wheel","tow truck","minibike","flat tire","squad car","car window","datsun","baggage car","vettura","minibus","lawn mower","sentra","pickup","freightliner","chevvy","miata","dump truck","sound truck","landrover","horseless carriage","hotrod","golf cart","taxicab","bubble car","tercel","headlight","mower","racecars","car mirror","armco","automobile engine","firetruck","powerboat","prius","econobox","windshield","automobile horn","dustcart","motor vehicle","drive around","fender","tipper lorry","pushchair","hubcap","freight car","driveway","hot rod","chevy","limousine","tipper truck","ragtop","altima","runabout","bumper","camaro","windscreen","dragster","trike","horsebox","motorboat","prang","refrigerator car","jinker","car wheel","police car","audi","gharry","trailer truck","car battery","bmw","manumotor","sport utility vehicle","toyota","microcar","brake drum","amaxophobia","volkswagen","electrical system","tank wagon","gas up","car door","road hog","car horn","cattle car","porsche","lexus","nissan","skibob","luggage carrier","vehiculation","prowl car","taxi stand","wagonage","slidometer","fan belt","motor pool","caravaneer","test driver","chrysler","gross weight","truckage","hummer","ferrari","motory","truck garden","chevrolet","diesel motor","panel van","honda","estate car","road train","ford","wheel around","black maria","wagonry","peirameter","idiot light","garageman","ahead","fast","neither","presently","new","private","big","black","red","used","open","armored","electric","armoured","blue","expensive","unmarked","stolen","nice","oncoming","empty","yellow","hired","fancy","compact","closed","shiny","wrecked","occasional","gray","damn","sleek","battered","rear","triumphal","crowded","stalled","cheap","luxurious","flashy","secondhand","fastest","bigger","abandoned","convertible","chauffeured","stationary","rental","sporty","overturned","loaded","newer","antique","aerial","lone","smart","hybrid","leading","inexpensive","damned","disabled","unfamiliar","goddamn","faster","refrigerated","runaway","locked","your","rusty","dusty","cheapest","dilapidated","interurban","powered","noisy","smashed","sealed","miniature","unlocked","lightweight","damaged","darkened","winning","suspicious","rickety","streamlined","cheaper","nicer","nondescript","economical","dependable","newest","covered","decorated","undercover","equipped","shabby","affordable","bulletproof","stranded","flaming","laden","mangled","junked","rented","enclosed","cramped","nosed","offending","goddamned","posh","ramshackle","roomy","burned","decrepit","submerged","dented","blazing","derailed","packed","goddam","demolished","rusted","winged","stylish","cute","crumpled","driverless","snazzy","stuffy","converted","beige","derelict","insured","safest","crushed","classy","clown","suspect","gilded","unsafe","overheated","spec","wheeled","customized","onrushing","supercharged","overcrowded","unattended","overloaded","unreliable","motorized","motionless","his","nicest","crappy","lousy","sexy","eastbound","futuristic","racy","stuck","waxed","junky","front","seat","back","side","house","door","top","window","home","sound","roof","price","speed","floor","wheels","lights","street","headlights","interior","owner","windows","make","damage","purchase","boot","trip","occupants","inside","sight","path","tires","corner","chauffeur","plane","keys","clothes","passengers","lead","travel","platform","doors","radio","walk");
    assertTrue(expectedWords.contains(actualWord));
  }

  @Test
  void testEmptyResponseFromDataMuseIsHandledCorrectly() {

    assertThrows(DataMuseException.class, () ->
        RandomWordGenerator.getRandomWord(new DataMuseRequest()));
  }
}