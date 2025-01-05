package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Objects;

public class CountryDetailsLanguages extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_languages);

        // Find the TextView to display country info
        ImageButton back_button = findViewById(R.id.back_button);
        Spinner countriesList = findViewById(R.id.language_spinner);
        TextView hellogoodbye = findViewById(R.id.hellogoodbye_translation);
        TextView pleasethankyou = findViewById(R.id.pleasethankyou_translation);
        TextView doyouspeak = findViewById(R.id.doyouspeak_translation);
        TextView whereis = findViewById(R.id.whereis_translation);
        TextView yesno = findViewById(R.id.yesno_translation);
        TextView excuseme = findViewById(R.id.excuseme_translation);
        TextView ineedhelp = findViewById(R.id.ineedhelp_translation);
        TextView directions = findViewById(R.id.directions_translation);

        String languages = getIntent().getStringExtra("languages");

        // Define your TextView references
        String[] mainLanguages = {
                getString(R.string.afrikaans),
                getString(R.string.albanian_language),
                getString(R.string.ethiopia_language),
                getString(R.string.arabic),
                getString(R.string.armenian_language),
                getString(R.string.azerbaijani_language),
                getString(R.string.aymara),
                getString(R.string.belarusian),
                getString(R.string.bengali),
                getString(R.string.berber),
                getString(R.string.bislama),
                getString(R.string.bosnian),
                getString(R.string.bulgarian_language),
                getString(R.string.myanmar_languages),
                getString(R.string.catalan_language),
                getString(R.string.chichewa),
                getString(R.string.chinese),
                getString(R.string.comorian),
                getString(R.string.croatia_language),
                getString(R.string.czech_language),
                getString(R.string.denmark_language),
                getString(R.string.dhivehi),
                getString(R.string.netherlands_languages),
                getString(R.string.dzongkha_language),
                getString(R.string.english),
                getString(R.string.estonia_languages),
                getString(R.string.fijian),
                getString(R.string.filipino),
                getString(R.string.finland_language),
                getString(R.string.french),
                getString(R.string.georgian_language),
                getString(R.string.german),
                getString(R.string.gilbertese),
                getString(R.string.greek),
                getString(R.string.guarani),
                getString(R.string.haitiancreole),
                getString(R.string.hebrew),
                getString(R.string.hindi),
                getString(R.string.hirimotu),
                getString(R.string.hungarian),
                getString(R.string.icelandic),
                getString(R.string.indonesian),
                getString(R.string.irish),
                getString(R.string.italian),
                getString(R.string.japanese),
                getString(R.string.kazakh),
                getString(R.string.khmer_language),
                getString(R.string.kinyarwanda),
                getString(R.string.kirundi),
                getString(R.string.korean),
                getString(R.string.kurdish),
                getString(R.string.kyrgyz),
                getString(R.string.laos_languages),
                getString(R.string.latvia_languages),
                getString(R.string.lithuania_languages),
                getString(R.string.luxembourgish),
                getString(R.string.north_macedonia_languages),
                getString(R.string.malagasy),
                getString(R.string.malay),
                getString(R.string.maltese),
                getString(R.string.maori),
                getString(R.string.marshallese),
                getString(R.string.mongolia_languages),
                getString(R.string.montenegro_languages),
                getString(R.string.nauran),
                getString(R.string.nepal_languages),
                getString(R.string.ndebele),
                getString(R.string.norway_languages),
                getString(R.string.palauan),
                getString(R.string.afghanistan_language),
                getString(R.string.persian),
                getString(R.string.poland_languages),
                getString(R.string.portuguese),
                getString(R.string.quechua),
                getString(R.string.romania_languages),
                getString(R.string.romansh),
                getString(R.string.russia_languages),
                getString(R.string.samoan),
                getString(R.string.sango),
                getString(R.string.serbia_languages),
                getString(R.string.sesotho),
                getString(R.string.setswana),
                getString(R.string.seychelloiscreole),
                getString(R.string.shona),
                getString(R.string.sinhala),
                getString(R.string.slovakia_languages),
                getString(R.string.slovenia_languages),
                getString(R.string.somali),
                getString(R.string.spanish),
                getString(R.string.swahili),
                getString(R.string.swazi),
                getString(R.string.sweden_languages),
                getString(R.string.tagalog),
                getString(R.string.tajikistan_languages),
                getString(R.string.tamil),
                getString(R.string.telugu),
                getString(R.string.tetum),
                getString(R.string.thailand_languages),
                getString(R.string.tigrinya),
                getString(R.string.tokpisin),
                getString(R.string.tongan),
                getString(R.string.turkey_languages),
                getString(R.string.turkmenistan_languages),
                getString(R.string.tuvaluan),
                getString(R.string.ukraine_languages),
                getString(R.string.urdu),
                getString(R.string.uzbek),
                getString(R.string.vietnamese),
                getString(R.string.xhosa),
                getString(R.string.zulu)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, mainLanguages);
        countriesList.setAdapter(adapter);

        int defaultPosition = 0;
        if (languages != null) {
            String firstLanguage = languages.split(",")[0].trim();
            defaultPosition = java.util.Arrays.asList(mainLanguages).indexOf(firstLanguage);
            if (defaultPosition == -1) defaultPosition = 0;
        }
        countriesList.setSelection(defaultPosition);

        countriesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedItem = parent.getItemAtPosition(position).toString();

                HashMap<String, Runnable> actions = new HashMap<>();

                actions.put(getString(R.string.afrikaans), () -> {
                    hellogoodbye.setText("Hallo / Totsiens");
                    pleasethankyou.setText("Asseblief / Dankie");
                    doyouspeak.setText("Praat jy... ?");
                    whereis.setText("Waar is... ?");
                    yesno.setText("Ja / Nee");
                    excuseme.setText("Verskoon my / Jammer");
                    ineedhelp.setText("Ek het hulp nodig");
                    directions.setText("Waar is die naaste bus-/treinstasie?");
                });
                actions.put(getString(R.string.albanian_language), () -> {
                    hellogoodbye.setText("Përshëndetje / Mirupafshim");
                    pleasethankyou.setText("Ju lutem / Faleminderit");
                    doyouspeak.setText("A flisni... ?");
                    whereis.setText("Ku është... ?");
                    yesno.setText("Po / Jo");
                    excuseme.setText("Më falni / Më vjen keq");
                    ineedhelp.setText("Kam nevojë për ndihmë");
                    directions.setText("Ku është stacioni më i afërt i autobusit / trenit?");
                });
                actions.put(getString(R.string.ethiopia_language), () -> {
                    hellogoodbye.setText("ሰላም / ደህና ሁን");
                    pleasethankyou.setText("እባኮትን / አመሰግናለሁ");
                    doyouspeak.setText("እርስዎ ትናገራለህ... ?");
                    whereis.setText("የት ነው... ?");
                    yesno.setText("አዎ / አይ");
                    excuseme.setText("ይቅርታ / ይቅርታ");
                    ineedhelp.setText("ማገዝ እፈልጋለሁ");
                    directions.setText("ቅርብ የአውቶቡስ / ባቡር ጣቢያ የት ነው?");
                });
                actions.put(getString(R.string.arabic), () -> {
                    hellogoodbye.setText("مرحبا / وداعا");
                    pleasethankyou.setText("من فضلك / شكرا");
                    doyouspeak.setText("هل تتحدث... ؟");
                    whereis.setText("أين... ؟");
                    yesno.setText("نعم / لا");
                    excuseme.setText("عفوا / آسف");
                    ineedhelp.setText("أحتاج إلى مساعدة");
                    directions.setText("أين أقرب محطة حافلات / قطار؟");
                });
                actions.put(getString(R.string.armenian_language), () -> {
                    hellogoodbye.setText("Բարեւ / Ցտեսություն");
                    pleasethankyou.setText("Խնդրում եմ / Շնորհակալություն");
                    doyouspeak.setText("Դուք խոսո՞ւմ եք... ?");
                    whereis.setText("Որտե՞ղ է... ?");
                    yesno.setText("Այո / Ոչ");
                    excuseme.setText("Ներեցեք / Կներեք");
                    ineedhelp.setText("Ես օգնության կարիք ունեմ");
                    directions.setText("Որտե՞ղ է մոտակա ավտոբուսի/գնացքի կայարանը:");
                });
                actions.put(getString(R.string.azerbaijani_language), () -> {
                    hellogoodbye.setText("Salam / Sağ olun");
                    pleasethankyou.setText("Zəhmət olmasa / Təşəkkür edirəm");
                    doyouspeak.setText("Siz... danışırsınız?");
                    whereis.setText("Haradadır... ?");
                    yesno.setText("Bəli / Xeyr");
                    excuseme.setText("Bağışlayın / Üzr istəyirəm");
                    ineedhelp.setText("Mənə kömək lazımdır");
                    directions.setText("Ən yaxın avtobus / qatar stansiyası haradadır?");
                });
                actions.put(getString(R.string.aymara), () -> {
                    hellogoodbye.setText("Kamisaraki / Jikisiñkama");
                    pleasethankyou.setText("Jisa / Yuspagara");
                    doyouspeak.setText("¿Juman parla... ?");
                    whereis.setText("¿Kawkiwa... ?");
                    yesno.setText("Arí / Jani");
                    excuseme.setText("Disculpame / Perdoname");
                    ineedhelp.setText("Jiwakiña nayjarux yanapt’añani");
                    directions.setText("¿Kawkiwa juk’ampi yaqha uta/trem lokala?");
                });
                actions.put(getString(R.string.belarusian), () -> {
                    hellogoodbye.setText("Добры дзень / Да пабачэння");
                    pleasethankyou.setText("Калі ласка / Дзякуй");
                    doyouspeak.setText("Вы размаўляеце па... ?");
                    whereis.setText("Дзе знаходзіцца... ?");
                    yesno.setText("Так / Не");
                    excuseme.setText("Прабачце / Вельмі шкада");
                    ineedhelp.setText("Мне патрэбна дапамога");
                    directions.setText("Дзе знаходзіцца найбліжэйшы аўтобусны / чыгуначны вакзал?");
                });
                actions.put(getString(R.string.bengali), () -> {
                    hellogoodbye.setText("হ্যালো / বিদায়");
                    pleasethankyou.setText("অনুগ্রহ করে / ধন্যবাদ");
                    doyouspeak.setText("আপনি কি বলেন... ?");
                    whereis.setText("কোথায়... ?");
                    yesno.setText("হ্যাঁ / না");
                    excuseme.setText("মাফ করবেন / দুঃখিত");
                    ineedhelp.setText("আমার সাহায্য দরকার");
                    directions.setText("নিকটতম বাস / ট্রেন স্টেশন কোথায়?");
                });
                actions.put(getString(R.string.berber), () -> {
                    hellogoodbye.setText("Azul / Ameggaz");
                    pleasethankyou.setText("Tanemmirt / Tanemmirt-nwen");
                    doyouspeak.setText("Tgam... ?");
                    whereis.setText("Anwa yellan... ?");
                    yesno.setText("Ih / Ahu");
                    excuseme.setText("Smah / Ghasad");
                    ineedhelp.setText("Aru tighri");
                    directions.setText("Anwa iḍum sṛan wayed yebnan / tameṭṭut ?");
                });
                actions.put(getString(R.string.bislama), () -> {
                    hellogoodbye.setText("Halo / Gudbae");
                    pleasethankyou.setText("Plis / Tangkiu");
                    doyouspeak.setText("Yu save toktok... ?");
                    whereis.setText("Wea... ?");
                    yesno.setText("Yes / No");
                    excuseme.setText("Sori / Mi sori");
                    ineedhelp.setText("Mi nid help");
                    directions.setText("Wea nao klosap bas / tren stesen?");
                });
                actions.put(getString(R.string.bosnian), () -> {
                    hellogoodbye.setText("Zdravo / Doviđenja");
                    pleasethankyou.setText("Molim / Hvala");
                    doyouspeak.setText("Govoriš li... ?");
                    whereis.setText("Gdje je... ?");
                    yesno.setText("Da / Ne");
                    excuseme.setText("Izvini / Žao mi je");
                    ineedhelp.setText("Treba mi pomoć");
                    directions.setText("Gdje je najbliža autobuska / željeznička stanica?");
                });
                actions.put(getString(R.string.bulgarian_language), () -> {
                    hellogoodbye.setText("Здравейте / Довиждане");
                    pleasethankyou.setText("Моля / Благодаря");
                    doyouspeak.setText("Говорите ли... ?");
                    whereis.setText("Къде е... ?");
                    yesno.setText("Да / Не");
                    excuseme.setText("Извинете / Съжалявам");
                    ineedhelp.setText("Имам нужда от помощ");
                    directions.setText("Къде е най-близката автобусна / железопътна станция?");
                });
                actions.put(getString(R.string.myanmar_languages), () -> { // Assuming Burmese
                    hellogoodbye.setText("မင်္ဂလာပါ / သွားတော့မယ်");
                    pleasethankyou.setText("ကျေးဇူးပြု၍ / ကျေးဇူးတင်ပါတယ်");
                    doyouspeak.setText("သင်... ပြောတတ်ပါသလား ?");
                    whereis.setText("ဘယ်မှာလဲ... ?");
                    yesno.setText("ဟုတ်ကဲ့ / မဟုတ်ဘူး");
                    excuseme.setText("ဆောရီးပါ / ဝမ်းနည်းပါတယ်");
                    ineedhelp.setText("ငါအကူအညီလိုပါတယ်");
                    directions.setText("အနီးဆုံး ဘတ်စ်ကား/ရထား စင်တာဘယ်မှာရှိလဲ?");
                });
                actions.put(getString(R.string.catalan_language), () -> {
                    hellogoodbye.setText("Hola / Adéu");
                    pleasethankyou.setText("Si us plau / Gràcies");
                    doyouspeak.setText("Parles... ?");
                    whereis.setText("On és... ?");
                    yesno.setText("Sí / No");
                    excuseme.setText("Perdona / Ho sento");
                    ineedhelp.setText("Necessito ajuda");
                    directions.setText("On és l'estació d'autobús/tren més propera?");
                });
                actions.put(getString(R.string.chichewa), () -> {
                    hellogoodbye.setText("Moni / Tsalani bwino");
                    pleasethankyou.setText("Chonde / Zikomo");
                    doyouspeak.setText("Mumalankhula... ?");
                    whereis.setText("Kuli... ?");
                    yesno.setText("Inde / Ayi");
                    excuseme.setText("Pepani / Ndine wokwiya");
                    ineedhelp.setText("Ndikufuna thandizo");
                    directions.setText("Kuli malo oyimitsa basi / sitima apafupi?");
                });
                actions.put(getString(R.string.chinese), () -> {
                    hellogoodbye.setText("你好 / 再见");
                    pleasethankyou.setText("请 / 谢谢");
                    doyouspeak.setText("你会说... 吗?");
                    whereis.setText("在哪里... ?");
                    yesno.setText("是 / 不是");
                    excuseme.setText("对不起 / 抱歉");
                    ineedhelp.setText("我需要帮助");
                    directions.setText("最近的巴士/火车站在哪里?");
                });
                actions.put(getString(R.string.comorian), () -> {
                    hellogoodbye.setText("Bwaridja / Kwaheri");
                    pleasethankyou.setText("Ndapenda / Asante");
                    doyouspeak.setText("Unasemaje... ?");
                    whereis.setText("Wapi... ?");
                    yesno.setText("Ndio / Hapana");
                    excuseme.setText("Samahani / Pole");
                    ineedhelp.setText("Nahitaji msaada");
                    directions.setText("Stesheni ya basi/treni karibu iko wapi?");
                });
                actions.put(getString(R.string.croatia_language), () -> {
                    hellogoodbye.setText("Pozdrav / Doviđenja");
                    pleasethankyou.setText("Molim / Hvala");
                    doyouspeak.setText("Govoriš li... ?");
                    whereis.setText("Gdje je... ?");
                    yesno.setText("Da / Ne");
                    excuseme.setText("Oprostite / Žao mi je");
                    ineedhelp.setText("Treba mi pomoć");
                    directions.setText("Gdje je najbliža autobusna / željeznička stanica?");
                });
                actions.put(getString(R.string.czech_language), () -> {
                    hellogoodbye.setText("Ahoj / Nashledanou");
                    pleasethankyou.setText("Prosím / Děkuji");
                    doyouspeak.setText("Mluvíte... ?");
                    whereis.setText("Kde je... ?");
                    yesno.setText("Ano / Ne");
                    excuseme.setText("Promiňte / Omlouvám se");
                    ineedhelp.setText("Potřebuji pomoc");
                    directions.setText("Kde je nejbližší autobusová / vlaková stanice?");
                });
                actions.put(getString(R.string.denmark_language), () -> {
                    hellogoodbye.setText("Hej / Farvel");
                    pleasethankyou.setText("Venligst / Tak");
                    doyouspeak.setText("Taler du... ?");
                    whereis.setText("Hvor er... ?");
                    yesno.setText("Ja / Nej");
                    excuseme.setText("Undskyld / Beklager");
                    ineedhelp.setText("Jeg har brug for hjælp");
                    directions.setText("Hvor er den nærmeste bus- / togstation?");
                });
                actions.put(getString(R.string.dhivehi), () -> {
                    hellogoodbye.setText("ހެލޯ / ފަނޑިޔޭ");
                    pleasethankyou.setText("ރަނގަޅުގެ / ޝުކުރާއްޖޭ");
                    doyouspeak.setText("ކޯސައިން... ?");
                    whereis.setText("އެވެން... ?");
                    yesno.setText("އަދި / އަދީން");
                    excuseme.setText("ކުރީއަށް / މަދައިރާތަކަށް");
                    ineedhelp.setText("އަދިވަގައި އަތޮޅުވާއަށް");
                    directions.setText("އެންނަގި އަތޮޅު ބައުސް / ޓްރެން ސްޓޭޝަން މަދައިރާތަކަށް ކޯސައިން?");
                });
                actions.put(getString(R.string.netherlands_languages), () -> {
                    hellogoodbye.setText("Hallo / Tot ziens");
                    pleasethankyou.setText("Alstublieft / Dank u");
                    doyouspeak.setText("Spreekt u... ?");
                    whereis.setText("Waar is... ?");
                    yesno.setText("Ja / Nee");
                    excuseme.setText("Excuseer me / Sorry");
                    ineedhelp.setText("Ik heb hulp nodig");
                    directions.setText("Waar is het dichtstbijzijnde bus- / treinstation?");
                });
                actions.put(getString(R.string.dzongkha_language), () -> {
                    hellogoodbye.setText("དགའ་བའི་གསུང་། / བརྗེ་གསུང་།");
                    pleasethankyou.setText("གནང་རོགས་ / བཀའ་བཀྲ་ཤིས་བརྒྱུད།");
                    doyouspeak.setText("ཁྱོད་སྐད་ཆ་སྨྱོང་ཡོད་ནམ་ ?");
                    whereis.setText("ག་གི་རེད་... ?");
                    yesno.setText("ཡོད་ནའི་ / མི་ཡོད།");
                    excuseme.setText("དགོས་པ། / རེ་མོས་བྱུང་།");
                    ineedhelp.setText("ང་རེ་བ་བྱེད་གོང་འབད་དགོས་ཡོད།");
                    directions.setText("བསྟེན་རིམ་གསུམ་བསྟེན་འབད་ས་དཀར་ཤིང་རེད་ཡིན་ན?");
                });
                actions.put(getString(R.string.english), () -> {
                    hellogoodbye.setText("Hello / Goodbye");
                    pleasethankyou.setText("Please / Thank you");
                    doyouspeak.setText("Do you speak... ?");
                    whereis.setText("Where is... ?");
                    yesno.setText("Yes / No");
                    excuseme.setText("Excuse me / Sorry");
                    ineedhelp.setText("I need help");
                    directions.setText("Where is nearest bus/train station?");
                });
                actions.put(getString(R.string.estonia_languages), () -> {
                    hellogoodbye.setText("Tere / Head aega");
                    pleasethankyou.setText("Palun / Aitäh");
                    doyouspeak.setText("Kas te räägite... ?");
                    whereis.setText("Kus on... ?");
                    yesno.setText("Jah / Ei");
                    excuseme.setText("Vabandage / Mul on kahju");
                    ineedhelp.setText("Ma vajan abi");
                    directions.setText("Kus asub lähim bussi- / rongijaam?");
                });
                actions.put(getString(R.string.fijian), () -> {
                    hellogoodbye.setText("Bula / Moce");
                    pleasethankyou.setText("Kerekere / Vinaka");
                    doyouspeak.setText("O kila na vosa vaka... ?");
                    whereis.setText("E vei... ?");
                    yesno.setText("Io / Sega");
                    excuseme.setText("Tulou / Vosoti au");
                    ineedhelp.setText("Au gadreva na veivuke");
                    directions.setText("E vei na basi/qiqi ni motoka voleka sara?");
                });
                actions.put(getString(R.string.filipino), () -> {
                    hellogoodbye.setText("Kamusta / Paalam");
                    pleasethankyou.setText("Pakiusap / Salamat");
                    doyouspeak.setText("Nagsasalita ka ba ng... ?");
                    whereis.setText("Nasaan ang... ?");
                    yesno.setText("Oo / Hindi");
                    excuseme.setText("Pasensya na / Paumanhin");
                    ineedhelp.setText("Kailangan ko ng tulong");
                    directions.setText("Nasaan ang pinakamalapit na bus/train station?");
                });
                actions.put(getString(R.string.finland_language), () -> {
                    hellogoodbye.setText("Hei / Näkemiin");
                    pleasethankyou.setText("Ole hyvä / Kiitos");
                    doyouspeak.setText("Puhutko... ?");
                    whereis.setText("Missä on... ?");
                    yesno.setText("Kyllä / Ei");
                    excuseme.setText("Anteeksi / Olen pahoillani");
                    ineedhelp.setText("Tarvitsen apua");
                    directions.setText("Missä on lähin bussi-/juna-asema?");
                });
                actions.put(getString(R.string.french), () -> {
                    hellogoodbye.setText("Bonjour / Au revoir");
                    pleasethankyou.setText("S'il vous plaît / Merci");
                    doyouspeak.setText("Parlez-vous... ?");
                    whereis.setText("Où est... ?");
                    yesno.setText("Oui / Non");
                    excuseme.setText("Excusez-moi / Désolé");
                    ineedhelp.setText("J'ai besoin d'aide");
                    directions.setText("Où est la station de bus/train la plus proche?");
                });
                actions.put(getString(R.string.georgian_language), () -> {
                    hellogoodbye.setText("გამარჯობა / ნახვამდის");
                    pleasethankyou.setText("გთხოვთ / გმადლობთ");
                    doyouspeak.setText("თქვენ საუბრობთ... ?");
                    whereis.setText("სად არის... ?");
                    yesno.setText("კი / არა");
                    excuseme.setText("ბოდიში / მაპატიეთ");
                    ineedhelp.setText("მჭირდება დახმარება");
                    directions.setText("სად არის უახლოესი ავტობუსის/მატარებლის სადგური?");
                });
                actions.put(getString(R.string.german), () -> {
                    hellogoodbye.setText("Hallo / Auf Wiedersehen");
                    pleasethankyou.setText("Bitte / Danke");
                    doyouspeak.setText("Sprechen Sie... ?");
                    whereis.setText("Wo ist... ?");
                    yesno.setText("Ja / Nein");
                    excuseme.setText("Entschuldigung / Es tut mir leid");
                    ineedhelp.setText("Ich brauche Hilfe");
                    directions.setText("Wo ist die nächste Bus-/Bahnhaltestelle?");
                });
                actions.put(getString(R.string.gilbertese), () -> {
                    hellogoodbye.setText("Ko na mauri / Ko na mauri ma teimatoa");
                    pleasethankyou.setText("Mauri / Ko rabwa");
                    doyouspeak.setText("Ko roko bwaia... ?");
                    whereis.setText("Iango... ?");
                    yesno.setText("E / Ti bo");
                    excuseme.setText("Ko kamea / Ko reirei");
                    ineedhelp.setText("I tangiraki tiina");
                    directions.setText("Iango taari buubuuan/matang ni te kabu?");
                });
                actions.put(getString(R.string.greek), () -> {
                    hellogoodbye.setText("Γεια σας / Αντίο");
                    pleasethankyou.setText("Παρακαλώ / Ευχαριστώ");
                    doyouspeak.setText("Μιλάτε... ?");
                    whereis.setText("Πού είναι... ?");
                    yesno.setText("Ναι / Όχι");
                    excuseme.setText("Με συγχωρείτε / Λυπάμαι");
                    ineedhelp.setText("Χρειάζομαι βοήθεια");
                    directions.setText("Πού είναι ο πλησιέστερος σταθμός λεωφορείου/τρένου;");
                });
                actions.put(getString(R.string.guarani), () -> {
                    hellogoodbye.setText("Mba'éichapa / Jajotopata");
                    pleasethankyou.setText("Eikoporã / Aguyje");
                    doyouspeak.setText("Reñe'êpa... ?");
                    whereis.setText("Moõpa... ?");
                    yesno.setText("Hee / Nahániri");
                    excuseme.setText("Eikopora / Eikopyta");
                    ineedhelp.setText("Aikoteve poipytyvô");
                    directions.setText("Moõpa oî pe estación de bus/train ipyahuvéva?");
                });
                actions.put(getString(R.string.haitiancreole), () -> {
                    hellogoodbye.setText("Bonjou / Orevwa");
                    pleasethankyou.setText("Souple / Mesi");
                    doyouspeak.setText("Èske ou pale... ?");
                    whereis.setText("Ki kote... ?");
                    yesno.setText("Wi / Non");
                    excuseme.setText("Eskize mwen / Mwen regrèt");
                    ineedhelp.setText("Mwen bezwen èd");
                    directions.setText("Ki kote estasyon bis/tren ki pi pre?");
                });
                actions.put(getString(R.string.hebrew), () -> {
                    hellogoodbye.setText("שלום / להתראות");
                    pleasethankyou.setText("בבקשה / תודה");
                    doyouspeak.setText("אתה מדבר... ?");
                    whereis.setText("איפה... ?");
                    yesno.setText("כן / לא");
                    excuseme.setText("סלח לי / אני מצטער");
                    ineedhelp.setText("אני צריך עזרה");
                    directions.setText("איפה תחנת האוטובוס/רכבת הקרובה ביותר?");
                });
                actions.put(getString(R.string.hindi), () -> {
                    hellogoodbye.setText("नमस्ते / अलविदा");
                    pleasethankyou.setText("कृपया / धन्यवाद");
                    doyouspeak.setText("क्या आप बोलते हैं... ?");
                    whereis.setText("कहाँ है... ?");
                    yesno.setText("हाँ / नहीं");
                    excuseme.setText("माफ़ कीजिए / मुझे खेद है");
                    ineedhelp.setText("मुझे मदद की जरूरत है");
                    directions.setText("निकटतम बस/ट्रेन स्टेशन कहाँ है?");
                });
                actions.put(getString(R.string.hirimotu), () -> {
                    hellogoodbye.setText("Hai / Lukim yu");
                    pleasethankyou.setText("Plis / Tenkyu");
                    doyouspeak.setText("Yu save toktok... ?");
                    whereis.setText("We stap... ?");
                    yesno.setText("Yes / Nogat");
                    excuseme.setText("Sori / Mi sori tru");
                    ineedhelp.setText("Mi nidim halivim");
                    directions.setText("We stap klostu bas / tren stesen?");
                });
                actions.put(getString(R.string.hungarian), () -> {
                    hellogoodbye.setText("Helló / Viszlát");
                    pleasethankyou.setText("Kérem / Köszönöm");
                    doyouspeak.setText("Beszélsz... ?");
                    whereis.setText("Hol van... ?");
                    yesno.setText("Igen / Nem");
                    excuseme.setText("Elnézést / Sajnálom");
                    ineedhelp.setText("Segítségre van szükségem");
                    directions.setText("Hol van a legközelebbi busz-/vonatállomás?");
                });
                actions.put(getString(R.string.icelandic), () -> {
                    hellogoodbye.setText("Halló / Bless");
                    pleasethankyou.setText("Vinsamlegast / Takk");
                    doyouspeak.setText("Talar þú... ?");
                    whereis.setText("Hvar er... ?");
                    yesno.setText("Já / Nei");
                    excuseme.setText("Afsakið / Fyrirgefðu");
                    ineedhelp.setText("Ég þarf hjálp");
                    directions.setText("Hvar er næsta strætó-/lestarstöð?");
                });
                actions.put(getString(R.string.indonesian), () -> {
                    hellogoodbye.setText("Halo / Selamat tinggal");
                    pleasethankyou.setText("Tolong / Terima kasih");
                    doyouspeak.setText("Apakah Anda berbicara... ?");
                    whereis.setText("Dimana... ?");
                    yesno.setText("Ya / Tidak");
                    excuseme.setText("Permisi / Maaf");
                    ineedhelp.setText("Saya butuh bantuan");
                    directions.setText("Dimana halte bus/stasiun kereta terdekat?");
                });
                actions.put(getString(R.string.irish), () -> {
                    hellogoodbye.setText("Dia duit / Slán");
                    pleasethankyou.setText("Le do thoil / Go raibh maith agat");
                    doyouspeak.setText("An labhraíonn tú... ?");
                    whereis.setText("Cá bhfuil... ?");
                    yesno.setText("Tá / Níl");
                    excuseme.setText("Gabh mo leithscéal / Tá brón orm");
                    ineedhelp.setText("Tá cabhair ag teastáil uaim");
                    directions.setText("Cá bhfuil an stáisiún bus / traenach is gaire?");
                });
                actions.put(getString(R.string.italian), () -> {
                    hellogoodbye.setText("Ciao / Arrivederci");
                    pleasethankyou.setText("Per favore / Grazie");
                    doyouspeak.setText("Parli... ?");
                    whereis.setText("Dov'è... ?");
                    yesno.setText("Sì / No");
                    excuseme.setText("Mi scusi / Mi dispiace");
                    ineedhelp.setText("Ho bisogno di aiuto");
                    directions.setText("Dov'è la stazione degli autobus / dei treni più vicina?");
                });
                actions.put(getString(R.string.japanese), () -> {
                    hellogoodbye.setText("こんにちは / さようなら");
                    pleasethankyou.setText("お願いします / ありがとう");
                    doyouspeak.setText("あなたは... を話せますか?");
                    whereis.setText("どこにありますか... ?");
                    yesno.setText("はい / いいえ");
                    excuseme.setText("すみません / ごめんなさい");
                    ineedhelp.setText("助けが必要です");
                    directions.setText("最寄りのバス/電車の駅はどこですか?");
                });
                actions.put(getString(R.string.kazakh), () -> {
                    hellogoodbye.setText("Сәлем / Сау болыңыз");
                    pleasethankyou.setText("Өтінемін / Рақмет");
                    doyouspeak.setText("Сіз сөйлейсіз бе... ?");
                    whereis.setText("Қайда... ?");
                    yesno.setText("Иә / Жоқ");
                    excuseme.setText("Кешіріңіз / Өкінішті");
                    ineedhelp.setText("Маған көмек керек");
                    directions.setText("Ең жақын автобус/пойыз станциясы қайда?");
                });
                actions.put(getString(R.string.khmer_language), () -> {
                    hellogoodbye.setText("សួស្តី / លាហើយ");
                    pleasethankyou.setText("សូម / អរគុណ");
                    doyouspeak.setText("តើអ្នកនិយាយ...ទេ?");
                    whereis.setText("ឯណា...?");
                    yesno.setText("បាទ/ចាស / ទេ");
                    excuseme.setText("សូមទោស / ខ្ញុំសោកស្តាយ");
                    ineedhelp.setText("ខ្ញុំត្រូវការជំនួយ");
                    directions.setText("ឯណាជាដំណាក់កាលរថយន្តក្រុង/រថភ្លើងដែលនៅជិតបំផុត?");
                });
                actions.put(getString(R.string.kinyarwanda), () -> {
                    hellogoodbye.setText("Muraho / Murabeho");
                    pleasethankyou.setText("Nyamuneka / Urakoze");
                    doyouspeak.setText("Uvuga... ?");
                    whereis.setText("Hehe... ?");
                    yesno.setText("Yego / Oya");
                    excuseme.setText("Mbabarira / Mbabajwe");
                    ineedhelp.setText("Nkeneye ubufasha");
                    directions.setText("Ni hehe hantu habarizwa bisi/garimoshi?");
                });
                actions.put(getString(R.string.kirundi), () -> {
                    hellogoodbye.setText("Bwakeye / Gutungana");
                    pleasethankyou.setText("Ndakwinginze / Urakoze");
                    doyouspeak.setText("Uvuga... ?");
                    whereis.setText("Ni hehe... ?");
                    yesno.setText("Ego / Oya");
                    excuseme.setText("Mbabarira / Ndababaye");
                    ineedhelp.setText("Nkeneye ubufasha");
                    directions.setText("Ni hehe hari bisi / gari ya moshi hafi?");
                });
                actions.put(getString(R.string.korean), () -> {
                    hellogoodbye.setText("안녕하세요 / 안녕히 가세요");
                    pleasethankyou.setText("제발 / 감사합니다");
                    doyouspeak.setText("당신은 ...를 말합니까?");
                    whereis.setText("어디에... 있습니까?");
                    yesno.setText("예 / 아니오");
                    excuseme.setText("죄송합니다 / 미안합니다");
                    ineedhelp.setText("도움이 필요합니다");
                    directions.setText("가장 가까운 버스/기차역이 어디입니까?");
                });
                actions.put(getString(R.string.kurdish), () -> {
                    hellogoodbye.setText("Slav / Bi xatirê te");
                    pleasethankyou.setText("Ji kerema xwe / Spas");
                    doyouspeak.setText("Te... dipivînî?");
                    whereis.setText("Li ku ye... ?");
                    yesno.setText("Erê / Na");
                    excuseme.setText("Bibore / Ez xêmgîn im");
                    ineedhelp.setText("Ez hewceyê alîkarîyê heye");
                    directions.setText("Li ku ye stasyonek nêzî ya otobûsê/anî?");
                });
                actions.put(getString(R.string.kyrgyz), () -> {
                    hellogoodbye.setText("Салам / Кош болуңуз");
                    pleasethankyou.setText("Сураныч / Рахмат");
                    doyouspeak.setText("Сиз сүйлөйсүзбү... ?");
                    whereis.setText("Кайда... ?");
                    yesno.setText("Ооба / Жок");
                    excuseme.setText("Кечиресиз / Кечирим сурайм");
                    ineedhelp.setText("Мага жардам керек");
                    directions.setText("Эң жакын автобус/пойыз станциясы кайда?");
                });
                actions.put(getString(R.string.laos_languages), () -> {
                    hellogoodbye.setText("ສະບາຍດີ / ລາກ່ອນ");
                    pleasethankyou.setText("ກະລຸນາ / ຂອບໃຈ");
                    doyouspeak.setText("ເຈົ້າເວົ້າພາສາ... ບໍ?");
                    whereis.setText("ຢູ່ໃສ... ?");
                    yesno.setText("ແມ່ນ / ບໍ່ແມ່ນ");
                    excuseme.setText("ຂໍໂທດ / ຂໍອະໄພ");
                    ineedhelp.setText("ຂ້ອຍຕ້ອງການຄວາມຊ່ອຍເຫຼືອ");
                    directions.setText("ສະຖານທີ່ທີ່ໃກ້ສຸດຂອງລົດເມ / ຂະບວນລົດໄຟຢູ່ໃສ?");
                });
                actions.put(getString(R.string.latvia_languages), () -> {
                    hellogoodbye.setText("Sveiki / Uz redzēšanos");
                    pleasethankyou.setText("Lūdzu / Paldies");
                    doyouspeak.setText("Vai jūs runājat... ?");
                    whereis.setText("Kur ir... ?");
                    yesno.setText("Jā / Nē");
                    excuseme.setText("Atvainojiet / Piedodiet");
                    ineedhelp.setText("Man vajag palīdzību");
                    directions.setText("Kur atrodas tuvākā autobusu/vilciena stacija?");
                });
                actions.put(getString(R.string.lithuania_languages), () -> {
                    hellogoodbye.setText("Labas / Viso gero");
                    pleasethankyou.setText("Prašau / Ačiū");
                    doyouspeak.setText("Ar jūs kalbate... ?");
                    whereis.setText("Kur yra... ?");
                    yesno.setText("Taip / Ne");
                    excuseme.setText("Atsiprašau / Man labai gaila");
                    ineedhelp.setText("Man reikia pagalbos");
                    directions.setText("Kur yra artimiausia autobusų/traukinių stotis?");
                });
                actions.put(getString(R.string.luxembourgish), () -> {
                    hellogoodbye.setText("Moien / Äddi");
                    pleasethankyou.setText("Wann ech gelift / Merci");
                    doyouspeak.setText("Schwätzt Dir... ?");
                    whereis.setText("Wou ass... ?");
                    yesno.setText("Jo / Nee");
                    excuseme.setText("Entschëllegt / Et deet mer leed");
                    ineedhelp.setText("Ech brauch Hëllef");
                    directions.setText("Wou ass den nächsten Bus-/Zuchstatioun?");
                });
                actions.put(getString(R.string.north_macedonia_languages), () -> {
                    hellogoodbye.setText("Здраво / Довидување");
                    pleasethankyou.setText("Ве молам / Благодарам");
                    doyouspeak.setText("Дали зборувате... ?");
                    whereis.setText("Каде е... ?");
                    yesno.setText("Да / Не");
                    excuseme.setText("Извинете / Жал ми е");
                    ineedhelp.setText("Ми треба помош");
                    directions.setText("Каде е најблиската автобуска/железничка станица?");
                });
                actions.put(getString(R.string.malagasy), () -> {
                    hellogoodbye.setText("Salama / Veloma");
                    pleasethankyou.setText("Azafady / Misaotra");
                    doyouspeak.setText("Miteny... ve ianao?");
                    whereis.setText("Aiza ny... ?");
                    yesno.setText("Eny / Tsia");
                    excuseme.setText("Azafady / Miala tsiny");
                    ineedhelp.setText("Mila fanampiana aho");
                    directions.setText("Aiza ny tobin'ny fiara fitaterana/fiarandalamby akaiky indrindra?");
                });
                actions.put(getString(R.string.malay), () -> {
                    hellogoodbye.setText("Hello / Selamat tinggal");
                    pleasethankyou.setText("Tolong / Terima kasih");
                    doyouspeak.setText("Adakah anda bercakap... ?");
                    whereis.setText("Di mana... ?");
                    yesno.setText("Ya / Tidak");
                    excuseme.setText("Maafkan saya / Maaf");
                    ineedhelp.setText("Saya perlukan bantuan");
                    directions.setText("Di manakah stesen bas/kereta api terdekat?");
                });
                actions.put(getString(R.string.maltese), () -> {
                    hellogoodbye.setText("Bonġu / Saħħa");
                    pleasethankyou.setText("Jekk jogħġbok / Grazzi");
                    doyouspeak.setText("Titkellem... ?");
                    whereis.setText("Fejn hu... ?");
                    yesno.setText("Iva / Le");
                    excuseme.setText("Skużani / Jiddispjaċini");
                    ineedhelp.setText("Għandi bżonn l-għajnuna");
                    directions.setText("Fejn hi l-eqreb stazzjon tal-karozzi tal-linja jew tal-ferrovija?");
                });
                actions.put(getString(R.string.maori), () -> {
                    hellogoodbye.setText("Kia ora / Haere rā");
                    pleasethankyou.setText("Tēnā koa / Kia ora");
                    doyouspeak.setText("E kōrero ana koe i te... ?");
                    whereis.setText("Kei hea... ?");
                    yesno.setText("Āe / Kāo");
                    excuseme.setText("Aroha mai / E pouri ana ahau");
                    ineedhelp.setText("Kei te hiahia awhina ahau");
                    directions.setText("Kei hea teihana pahi/tereina tata rawa?");
                });
                actions.put(getString(R.string.marshallese), () -> {
                    hellogoodbye.setText("Yokwe / Bar lo eok");
                    pleasethankyou.setText("Joļok / Kommool");
                    doyouspeak.setText("Ewor kwaar kajin... ?");
                    whereis.setText("Eweo... ?");
                    yesno.setText("Et / Jab");
                    excuseme.setText("Kom̧m̧ool tata / Jolok am kajitok");
                    ineedhelp.setText("Ij aikuij jibwe");
                    directions.setText("Eweo buun / traank tok jikin?");
                });
                actions.put(getString(R.string.mongolia_languages), () -> {
                    hellogoodbye.setText("Сайн уу / Баяртай");
                    pleasethankyou.setText("Гуйя / Баярлалаа");
                    doyouspeak.setText("Та ярьдаг уу... ?");
                    whereis.setText("Хаана байна... ?");
                    yesno.setText("Тийм / Үгүй");
                    excuseme.setText("Уучлаарай / Намайг уучлаарай");
                    ineedhelp.setText("Надад тусламж хэрэгтэй байна");
                    directions.setText("Хамгийн ойрын автобус/галт тэрэгний буудал хаана байна?");
                });
                actions.put(getString(R.string.montenegro_languages), () -> {
                    hellogoodbye.setText("Zdravo / Doviđenja");
                    pleasethankyou.setText("Molim / Hvala");
                    doyouspeak.setText("Da li govorite... ?");
                    whereis.setText("Gdje je... ?");
                    yesno.setText("Da / Ne");
                    excuseme.setText("Izvinite / Žao mi je");
                    ineedhelp.setText("Treba mi pomoć");
                    directions.setText("Gdje je najbliža autobuska/željeznička stanica?");
                });
                actions.put(getString(R.string.nauran), () -> {
                    hellogoodbye.setText("Nim / Aera");
                    pleasethankyou.setText("Mea / Tengy");
                    doyouspeak.setText("Anga ian eoran... ?");
                    whereis.setText("Eiyouwe... ?");
                    yesno.setText("E / Tsin");
                    excuseme.setText("Duk / Kaburai");
                    ineedhelp.setText("Anga itabo bwiora");
                    directions.setText("Anga bweij bwamuda bwiora?");
                });
                actions.put(getString(R.string.nepal_languages), () -> { // Assuming Nepali
                    hellogoodbye.setText("नमस्ते / बिदाई");
                    pleasethankyou.setText("कृपया / धन्यवाद");
                    doyouspeak.setText("तपाईं बोल्नुहुन्छ... ?");
                    whereis.setText("कहाँ छ... ?");
                    yesno.setText("हो / होईन");
                    excuseme.setText("माफ गर्नुहोस् / मलाई दुख लागेको छ");
                    ineedhelp.setText("मलाई सहयोग चाहिन्छ");
                    directions.setText("निकटतम बस/रेलवे स्टेशन कहाँ छ?");
                });
                actions.put(getString(R.string.ndebele), () -> {
                    hellogoodbye.setText("Sawubona / Sala kahle");
                    pleasethankyou.setText("Ngiyacela / Ngiyabonga");
                    doyouspeak.setText("Ukhuluma... ?");
                    whereis.setText("Kuphi... ?");
                    yesno.setText("Yebo / Cha");
                    excuseme.setText("Uxolo / Ngiyaxolisa");
                    ineedhelp.setText("Ngidinga usizo");
                    directions.setText("Kuphi isiteshi esiseduze sebhasi / sika-loliwe?");
                });
                actions.put(getString(R.string.norway_languages), () -> {
                    hellogoodbye.setText("Hallo / Ha det");
                    pleasethankyou.setText("Vær så snill / Takk");
                    doyouspeak.setText("Snakker du... ?");
                    whereis.setText("Hvor er... ?");
                    yesno.setText("Ja / Nei");
                    excuseme.setText("Unnskyld / Beklager");
                    ineedhelp.setText("Jeg trenger hjelp");
                    directions.setText("Hvor er nærmeste buss- / togstasjon?");
                });
                actions.put(getString(R.string.palauan), () -> {
                    hellogoodbye.setText("Alii / Ungil el cherung");
                    pleasethankyou.setText("Mechikung / Kom kmal mesulang");
                    doyouspeak.setText("Mlekoi... ?");
                    whereis.setText("Ng mla... ?");
                    yesno.setText("Ngai / Bo");
                    excuseme.setText("Bai / Mesulang");
                    ineedhelp.setText("Ak merrou a beches");
                    directions.setText("Ng mla suub el ngukes/tekoi?");
                });
                actions.put(getString(R.string.afghanistan_language), () -> { // Assuming Dari
                    hellogoodbye.setText("سلام / خداحافظ");
                    pleasethankyou.setText("لطفاً / تشکر");
                    doyouspeak.setText("آیا شما صحبت می کنید... ?");
                    whereis.setText("کجا است... ?");
                    yesno.setText("بله / نه");
                    excuseme.setText("ببخشید / متأسفم");
                    ineedhelp.setText("من به کمک نیاز دارم");
                    directions.setText("نزدیکترین ایستگاه اتوبوس/قطار کجاست؟");
                });
                actions.put(getString(R.string.persian), () -> {
                    hellogoodbye.setText("سلام / خداحافظ");
                    pleasethankyou.setText("لطفاً / ممنون");
                    doyouspeak.setText("آیا شما صحبت می کنید... ?");
                    whereis.setText("کجا است... ?");
                    yesno.setText("بله / نه");
                    excuseme.setText("ببخشید / متأسفم");
                    ineedhelp.setText("من به کمک نیاز دارم");
                    directions.setText("نزدیکترین ایستگاه اتوبوس/قطار کجاست؟");
                });
                actions.put(getString(R.string.poland_languages), () -> {
                    hellogoodbye.setText("Cześć / Do widzenia");
                    pleasethankyou.setText("Proszę / Dziękuję");
                    doyouspeak.setText("Czy mówisz po... ?");
                    whereis.setText("Gdzie jest... ?");
                    yesno.setText("Tak / Nie");
                    excuseme.setText("Przepraszam / Przykro mi");
                    ineedhelp.setText("Potrzebuję pomocy");
                    directions.setText("Gdzie jest najbliższy dworzec autobusowy/kolejowy?");
                });
                actions.put(getString(R.string.portuguese), () -> {
                    hellogoodbye.setText("Olá / Adeus");
                    pleasethankyou.setText("Por favor / Obrigado");
                    doyouspeak.setText("Você fala... ?");
                    whereis.setText("Onde está... ?");
                    yesno.setText("Sim / Não");
                    excuseme.setText("Com licença / Desculpe");
                    ineedhelp.setText("Eu preciso de ajuda");
                    directions.setText("Onde fica a estação de ônibus/trem mais próxima?");
                });
                actions.put(getString(R.string.quechua), () -> {
                    hellogoodbye.setText("Rimaykullayki / Tupananchiskama");
                    pleasethankyou.setText("Allichu / Añay");
                    doyouspeak.setText("Qan rimayta yachankichu... ?");
                    whereis.setText("Maypin kan... ?");
                    yesno.setText("Arí / Mana");
                    excuseme.setText("Dispulpay / Llapananchikta");
                    ineedhelp.setText("Yanapananchikta munani");
                    directions.setText("Maypin kan tukuyqhatu/puquyqhatu más cerca?");
                });
                actions.put(getString(R.string.romania_languages), () -> {
                    hellogoodbye.setText("Bună / La revedere");
                    pleasethankyou.setText("Vă rog / Mulțumesc");
                    doyouspeak.setText("Vorbiți... ?");
                    whereis.setText("Unde este... ?");
                    yesno.setText("Da / Nu");
                    excuseme.setText("Scuzați-mă / Îmi pare rău");
                    ineedhelp.setText("Am nevoie de ajutor");
                    directions.setText("Unde este cea mai apropiată stație de autobuz/tren?");
                });
                actions.put(getString(R.string.romansh), () -> {
                    hellogoodbye.setText("Allegra / Adia");
                    pleasethankyou.setText("Per plaschair / Grazia");
                    doyouspeak.setText("Tschintscheis... ?");
                    whereis.setText("Nua è... ?");
                    yesno.setText("Gea / Na");
                    excuseme.setText("Pardunai / Regret");
                    ineedhelp.setText("Duvrel agid");
                    directions.setText("Nua è la staziun d’autos / tren la pli vischin?");
                });
                actions.put(getString(R.string.russia_languages), () -> {
                    hellogoodbye.setText("Здравствуйте / До свидания");
                    pleasethankyou.setText("Пожалуйста / Спасибо");
                    doyouspeak.setText("Вы говорите по... ?");
                    whereis.setText("Где находится... ?");
                    yesno.setText("Да / Нет");
                    excuseme.setText("Извините / Простите");
                    ineedhelp.setText("Мне нужна помощь");
                    directions.setText("Где находится ближайшая автобусная/железнодорожная станция?");
                });
                actions.put(getString(R.string.samoan), () -> {
                    hellogoodbye.setText("Talofa / Tofa");
                    pleasethankyou.setText("Fa’amolemole / Fa’afetai");
                    doyouspeak.setText("E te tautala... ?");
                    whereis.setText("O fea le... ?");
                    yesno.setText("Ioe / Leai");
                    excuseme.setText("Fa’amalie atu / Fa’atoese");
                    ineedhelp.setText("Ou te mana’omia se fesoasoani");
                    directions.setText("O fea o iai le nofoaga latalata mo le pasi/ nofoaafi?");
                });
                actions.put(getString(R.string.sango), () -> {
                    hellogoodbye.setText("Bara na mbongui / Yi àse");
                    pleasethankyou.setText("Ndakala / Singila");
                    doyouspeak.setText("Mu yeke zongbo ti... ?");
                    whereis.setText("Mu na lo... ?");
                    yesno.setText("Eè / Awa");
                    excuseme.setText("Bikiti / Songo");
                    ineedhelp.setText("Ngai me yeke bata");
                    directions.setText("Mu na lo gara ti bus/train iye?");
                });
                actions.put(getString(R.string.serbia_languages), () -> {
                    hellogoodbye.setText("Здраво / Довиђења");
                    pleasethankyou.setText("Молим / Хвала");
                    doyouspeak.setText("Да ли говорите... ?");
                    whereis.setText("Где је... ?");
                    yesno.setText("Да / Не");
                    excuseme.setText("Извините / Жао ми је");
                    ineedhelp.setText("Треба ми помоћ");
                    directions.setText("Где је најближа аутобуска/железничка станица?");
                });
                actions.put(getString(R.string.sesotho), () -> {
                    hellogoodbye.setText("Lumela / Sala hantle");
                    pleasethankyou.setText("Ka kopo / Kea leboha");
                    doyouspeak.setText("Na u bua... ?");
                    whereis.setText("Ho kae... ?");
                    yesno.setText("E / Che");
                    excuseme.setText("Ntšoarele / Ke masoabi");
                    ineedhelp.setText("Ke hloka thuso");
                    directions.setText("Ho kae seteishene sa libese / terene se haufi?");
                });
                actions.put(getString(R.string.setswana), () -> {
                    hellogoodbye.setText("Dumela / Tsamaya sentle");
                    pleasethankyou.setText("Tsweetswee / Ke a leboga");
                    doyouspeak.setText("O bua... ?");
                    whereis.setText("Kwa ke... ?");
                    yesno.setText("Ee / Nnyaa");
                    excuseme.setText("Intshwarele / Ke maswabi");
                    ineedhelp.setText("Ke tlhoka thuso");
                    directions.setText("Kwa go leng gaufi le seteishene sa bese / terene?");
                });
                actions.put(getString(R.string.seychelloiscreole), () -> {
                    hellogoodbye.setText("Bonzour / Orevwar");
                    pleasethankyou.setText("Silvouple / Mersi");
                    doyouspeak.setText("Ou koz... ?");
                    whereis.setText("Kote i... ?");
                    yesno.setText("Wi / Non");
                    excuseme.setText("Eskiz mwan / Mon dezole");
                    ineedhelp.setText("Mon bezwen led");
                    directions.setText("Kote stasyon bis/tren pli pre ?");
                });
                actions.put(getString(R.string.shona), () -> {
                    hellogoodbye.setText("Mhoro / Sara mushe");
                    pleasethankyou.setText("Ndapota / Ndatenda");
                    doyouspeak.setText("Unotaura... ?");
                    whereis.setText("Papi... ?");
                    yesno.setText("Hongu / Kwete");
                    excuseme.setText("Pamusoroi / Ndine urombo");
                    ineedhelp.setText("Ndoda rubatsiro");
                    directions.setText("Chiteshi chebhazi / chitima chiri papi?");
                });
                actions.put(getString(R.string.sinhala), () -> {
                    hellogoodbye.setText("ආයුබෝවන් / ගිහිල්ලා එන්න");
                    pleasethankyou.setText("කරුණාකර / ස්තුතියි");
                    doyouspeak.setText("ඔබට... කතා කරන්නේද?");
                    whereis.setText("කොහේද... ?");
                    yesno.setText("ඔව් / නැහැ");
                    excuseme.setText("මට සමාවෙන්න / මට කණගාටුයි");
                    ineedhelp.setText("මට උපකාරයක් අවශ්‍යයි");
                    directions.setText("ආසන්නම බස් / දුම්රිය ස්ථානය කොහේද?");
                });
                actions.put(getString(R.string.slovakia_languages), () -> {
                    hellogoodbye.setText("Ahoj / Dovidenia");
                    pleasethankyou.setText("Prosím / Ďakujem");
                    doyouspeak.setText("Hovoríte... ?");
                    whereis.setText("Kde je... ?");
                    yesno.setText("Áno / Nie");
                    excuseme.setText("Prepáčte / Je mi ľúto");
                    ineedhelp.setText("Potrebujem pomoc");
                    directions.setText("Kde je najbližšia autobusová / železničná stanica?");
                });
                actions.put(getString(R.string.slovenia_languages), () -> {
                    hellogoodbye.setText("Živjo / Nasvidenje");
                    pleasethankyou.setText("Prosim / Hvala");
                    doyouspeak.setText("Ali govorite... ?");
                    whereis.setText("Kje je... ?");
                    yesno.setText("Da / Ne");
                    excuseme.setText("Oprostite / Žal mi je");
                    ineedhelp.setText("Potrebujem pomoč");
                    directions.setText("Kje je najbližja avtobusna/železniška postaja?");
                });
                actions.put(getString(R.string.somali), () -> {
                    hellogoodbye.setText("Salaan / Nabadgelyo");
                    pleasethankyou.setText("Fadlan / Mahadsanid");
                    doyouspeak.setText("Ma ku hadashaa... ?");
                    whereis.setText("Xaggee ku yaal... ?");
                    yesno.setText("Haa / Maya");
                    excuseme.setText("Iga raali ahow / Waan ka xumahay");
                    ineedhelp.setText("Caawin ayaan u baahanahay");
                    directions.setText("Xaggee u dhow saldhigga basaska / tareennada?");
                });
                actions.put(getString(R.string.spanish), () -> {
                    hellogoodbye.setText("Hola / Adiós");
                    pleasethankyou.setText("Por favor / Gracias");
                    doyouspeak.setText("¿Hablas... ?");
                    whereis.setText("¿Dónde está... ?");
                    yesno.setText("Sí / No");
                    excuseme.setText("Perdón / Lo siento");
                    ineedhelp.setText("Necesito ayuda");
                    directions.setText("¿Dónde está la estación de autobús/tren más cercana?");
                });
                actions.put(getString(R.string.swahili), () -> {
                    hellogoodbye.setText("Habari / Kwa heri");
                    pleasethankyou.setText("Tafadhali / Asante");
                    doyouspeak.setText("Unazungumza... ?");
                    whereis.setText("Iko wapi... ?");
                    yesno.setText("Ndio / Hapana");
                    excuseme.setText("Samahani / Pole");
                    ineedhelp.setText("Nahitaji msaada");
                    directions.setText("Iko wapi kituo cha basi/treni kilicho karibu zaidi?");
                });
                actions.put(getString(R.string.swazi), () -> {
                    hellogoodbye.setText("Sawubona / Hamba kahle");
                    pleasethankyou.setText("Ngicela / Ngiyabonga");
                    doyouspeak.setText("Ukhuluma... ?");
                    whereis.setText("Ikuphi... ?");
                    yesno.setText("Yebo / Cha");
                    excuseme.setText("Ngiyaxolisa / Ngiyadabuka");
                    ineedhelp.setText("Ngidinga usizo");
                    directions.setText("Ikuphi isiteshi esiseduze sebhasi / sikaloliwe?");
                });
                actions.put(getString(R.string.sweden_languages), () -> {
                    hellogoodbye.setText("Hej / Hejdå");
                    pleasethankyou.setText("Snälla / Tack");
                    doyouspeak.setText("Talar du... ?");
                    whereis.setText("Var är... ?");
                    yesno.setText("Ja / Nej");
                    excuseme.setText("Ursäkta / Förlåt");
                    ineedhelp.setText("Jag behöver hjälp");
                    directions.setText("Var är närmaste buss-/tågstation?");
                });
                actions.put(getString(R.string.tagalog), () -> {
                    hellogoodbye.setText("Kamusta / Paalam");
                    pleasethankyou.setText("Pakiusap / Salamat");
                    doyouspeak.setText("Nagsasalita ka ba ng... ?");
                    whereis.setText("Nasaan ang... ?");
                    yesno.setText("Oo / Hindi");
                    excuseme.setText("Pasensya na / Paumanhin");
                    ineedhelp.setText("Kailangan ko ng tulong");
                    directions.setText("Nasaan ang pinakamalapit na bus/tren station?");
                });
                actions.put(getString(R.string.tajikistan_languages), () -> {
                    hellogoodbye.setText("Салом / Хайр");
                    pleasethankyou.setText("Лутфан / Ташаккур");
                    doyouspeak.setText("Шумо бо... гап мезанед?");
                    whereis.setText("Дар куҷост... ?");
                    yesno.setText("Бале / Не");
                    excuseme.setText("Мебахшед / Ман афсус мехӯрам");
                    ineedhelp.setText("Ба ман кӯмак лозим аст");
                    directions.setText("Стансияи наздиктарин автобус/поезд дар куҷост?");
                });
                actions.put(getString(R.string.tamil), () -> {
                    hellogoodbye.setText("வணக்கம் / குட்பை");
                    pleasethankyou.setText("தயவு செய்து / நன்றி");
                    doyouspeak.setText("நீங்கள் பேசுகிறீர்களா... ?");
                    whereis.setText("எங்கு உள்ளது... ?");
                    yesno.setText("ஆம் / இல்லை");
                    excuseme.setText("மன்னிக்கவும் / நான் வருந்துகிறேன்");
                    ineedhelp.setText("எனக்கு உதவி தேவை");
                    directions.setText("எங்கு அருகிலுள்ள பேருந்து/ரயில் நிலையம் உள்ளது?");
                });
                actions.put(getString(R.string.telugu), () -> {
                    hellogoodbye.setText("నమస్తే / వీడ్కోలు");
                    pleasethankyou.setText("దయచేసి / ధన్యవాదాలు");
                    doyouspeak.setText("మీరు మాట్లాడతారా... ?");
                    whereis.setText("ఎక్కడ ఉంది... ?");
                    yesno.setText("అవును / కాదు");
                    excuseme.setText("క్షమించండి / నన్ను క్షమించండి");
                    ineedhelp.setText("నాకు సహాయం కావాలి");
                    directions.setText("సమీపంలో ఉన్న బస్/ట్రైన్ స్టేషన్ ఎక్కడ ఉంది?");
                });
                actions.put(getString(R.string.tetum), () -> {
                    hellogoodbye.setText("Bondia / Adeus");
                    pleasethankyou.setText("Favór / Obrigadu");
                    doyouspeak.setText("Ita koalia... ?");
                    whereis.setText("Iha ne'ebe... ?");
                    yesno.setText("Sin / Lae");
                    excuseme.setText("Deskulpa / Hau senti uluk");
                    ineedhelp.setText("Hau presiza ajuda");
                    directions.setText("Iha ne'ebe fatin kotuk/kareta dosi ne'ebe sedu?");
                });
                actions.put(getString(R.string.thailand_languages), () -> {
                    hellogoodbye.setText("สวัสดี / ลาก่อน");
                    pleasethankyou.setText("กรุณา / ขอบคุณ");
                    doyouspeak.setText("คุณพูด... ได้ไหม?");
                    whereis.setText("อยู่ที่ไหน... ?");
                    yesno.setText("ใช่ / ไม่ใช่");
                    excuseme.setText("ขอโทษ / ฉันเสียใจ");
                    ineedhelp.setText("ฉันต้องการความช่วยเหลือ");
                    directions.setText("สถานีรถบัส/รถไฟที่ใกล้ที่สุดอยู่ที่ไหน?");
                });
                actions.put(getString(R.string.tigrinya), () -> {
                    hellogoodbye.setText("ሰላም / ቻው");
                    pleasethankyou.setText("በአልቦን በታቦት / የበልኪ");
                    doyouspeak.setText("ንምክርክርን ንተንገራን ተባባለቕን?");
                    whereis.setText("ኣብን ኣየ?");
                    yesno.setText("እወ / ኣይወ");
                    excuseme.setText("ተዓዘበኒ / ይቐንየኒ");
                    ineedhelp.setText("ናብን ሓገዝ ፈልጥካኒ");
                    directions.setText("ኣብን ኩሉ ዝበለይተ ባስ/ባቡር ስመይት ኣየ?");
                });
                actions.put(getString(R.string.tokpisin), () -> {
                    hellogoodbye.setText("Gude / Lukim yu");
                    pleasethankyou.setText("Plis / Tenkyu");
                    doyouspeak.setText("Yu save toktok... ?");
                    whereis.setText("We yu stap... ?");
                    yesno.setText("Yes / Nogat");
                    excuseme.setText("Mi sori / Mi sori tru");
                    ineedhelp.setText("Mi nidim halivim");
                    directions.setText("We stap klostu bas / tren stesen?");
                });
                actions.put(getString(R.string.tongan), () -> {
                    hellogoodbye.setText("Mālō e lelei / Faka‘au a");
                    pleasethankyou.setText("Kataki / Mālō");
                    doyouspeak.setText("‘Oku ke lea... ?");
                    whereis.setText("Ko e fe... ?");
                    yesno.setText("‘Io / ‘Ikai");
                    excuseme.setText("Fakamolemole / Faka‘apa‘apa atu");
                    ineedhelp.setText("‘Oku ou fiema‘u tokoni");
                    directions.setText("Ko e fe e feitu‘u nofo‘anga pusipeka/paloti vave taha?");
                });
                actions.put(getString(R.string.turkey_languages), () -> {
                    hellogoodbye.setText("Merhaba / Hoşça kal");
                    pleasethankyou.setText("Lütfen / Teşekkürler");
                    doyouspeak.setText("Siz... konuşuyor musunuz?");
                    whereis.setText("Nerede... ?");
                    yesno.setText("Evet / Hayır");
                    excuseme.setText("Affedersiniz / Üzgünüm");
                    ineedhelp.setText("Yardıma ihtiyacım var");
                    directions.setText("En yakın otobüs/tren istasyonu nerede?");
                });
                actions.put(getString(R.string.turkmenistan_languages), () -> {
                    hellogoodbye.setText("Salam / Hoş gal");
                    pleasethankyou.setText("Görkeziniz / Sag boluň");
                    doyouspeak.setText("Siz... gepleýärsiňizmi?");
                    whereis.setText("Nirde... ?");
                    yesno.setText("Hawa / Ýok");
                    excuseme.setText("Bagyşlaň / Ökünýärin");
                    ineedhelp.setText("Maňa kömek gerek");
                    directions.setText("Iň ýakyn awtobus/poezd duralgasy nirede?");
                });
                actions.put(getString(R.string.tuvaluan), () -> {
                    hellogoodbye.setText("Talofa / Tofa");
                    pleasethankyou.setText("Fakamolemole / Fakafetai");
                    doyouspeak.setText("E iloa ne koe... ?");
                    whereis.setText("Tei fe... ?");
                    yesno.setText("Ioe / Ikai");
                    excuseme.setText("Fakamolemole / Fakaaaloalo");
                    ineedhelp.setText("E manako au ki se fesoasoani");
                    directions.setText("Tei fe te nofoaga o pasi/faletonu e pili atu?");
                });
                actions.put(getString(R.string.ukraine_languages), () -> {
                    hellogoodbye.setText("Привіт / До побачення");
                    pleasethankyou.setText("Будь ласка / Дякую");
                    doyouspeak.setText("Ви говорите... ?");
                    whereis.setText("Де знаходиться... ?");
                    yesno.setText("Так / Ні");
                    excuseme.setText("Вибачте / Мені шкода");
                    ineedhelp.setText("Мені потрібна допомога");
                    directions.setText("Де знаходиться найближча автобусна/залізнична станція?");
                });
                actions.put(getString(R.string.urdu), () -> {
                    hellogoodbye.setText("سلام / خداحافظ");
                    pleasethankyou.setText("براہ کرم / شکریہ");
                    doyouspeak.setText("کیا آپ بولتے ہیں... ?");
                    whereis.setText("کہاں ہے... ?");
                    yesno.setText("ہاں / نہیں");
                    excuseme.setText("معاف کیجیے / مجھے افسوس ہے");
                    ineedhelp.setText("مجھے مدد کی ضرورت ہے");
                    directions.setText("قریب ترین بس / ٹرین اسٹیشن کہاں ہے؟");
                });
                actions.put(getString(R.string.uzbek), () -> {
                    hellogoodbye.setText("Salom / Xayr");
                    pleasethankyou.setText("Iltimos / Rahmat");
                    doyouspeak.setText("Siz... gapira olasizmi?");
                    whereis.setText("Qaerda... ?");
                    yesno.setText("Ha / Yo'q");
                    excuseme.setText("Kechirasiz / Afsusdaman");
                    ineedhelp.setText("Menga yordam kerak");
                    directions.setText("Eng yaqin avtobus/poezd stansiyasi qayerda?");
                });
                actions.put(getString(R.string.vietnamese), () -> {
                    hellogoodbye.setText("Xin chào / Tạm biệt");
                    pleasethankyou.setText("Làm ơn / Cảm ơn");
                    doyouspeak.setText("Bạn có nói... không?");
                    whereis.setText("Ở đâu có... ?");
                    yesno.setText("Có / Không");
                    excuseme.setText("Xin lỗi / Tôi xin lỗi");
                    ineedhelp.setText("Tôi cần sự giúp đỡ");
                    directions.setText("Trạm xe buýt/tàu gần nhất ở đâu?");
                });
                actions.put(getString(R.string.xhosa), () -> {
                    hellogoodbye.setText("Mholo / Sala kakuhle");
                    pleasethankyou.setText("Nceda / Enkosi");
                    doyouspeak.setText("Uthetha isi... ?");
                    whereis.setText("Phi... ?");
                    yesno.setText("Ewe / Hayi");
                    excuseme.setText("Uxolo / Ndicela uxolo");
                    ineedhelp.setText("Ndidinga uncedo");
                    directions.setText("Phi isikhululo sebhasi / sikaloliwe esikufuphi?");
                });
                actions.put(getString(R.string.zulu), () -> {
                    hellogoodbye.setText("Sawubona / Sala kahle");
                    pleasethankyou.setText("Ngiyacela / Ngiyabonga");
                    doyouspeak.setText("Ukhuluma... ?");
                    whereis.setText("Ikuphi... ?");
                    yesno.setText("Yebo / Cha");
                    excuseme.setText("Uxolo / Ngiyaxolisa");
                    ineedhelp.setText("Ngidinga usizo");
                    directions.setText("Ikuphi isiteshi esiseduze sebhasi / sikaloliwe?");
                });

                Runnable action = actions.get(selectedItem);
                assert action != null;
                action.run();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected
            }
        });

        back_button.setOnClickListener(v -> {
            finish();
        });
    }
}