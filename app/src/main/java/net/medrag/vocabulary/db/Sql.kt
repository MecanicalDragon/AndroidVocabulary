package net.medrag.vocabulary.db


const val DATABASE = "DATABASE"
const val DATABASE_NAME = "MyVocabulary.db"
const val TABLE_NAME = "VOCAB"
const val ID = "ID"
const val WORD = "WORD"
const val TRANSLATION = "TRANSLATION"
const val STREAK = "STREAK"
const val LEARNED = "LEARNED"

const val CREATE =
    "create table if not exists $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $WORD TEXT, $TRANSLATION TEXT, $STREAK INTEGER, $LEARNED NUMERIC);"
const val FILL =
    "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('interact','взаимодействовать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('kernel','ядро, зерно, суть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('schedule','планировать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('attempt','попытка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ensure','гарантировать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('permission','разрешение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ad hoc','специально для этого');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('passion','страсть, азарт');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sane','здравомыслящий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('chunk','кусок, ломоть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('involve','вовлекать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('assume','предполагать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('toil','усердно работать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('evaluate','оценивать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('deception','обман');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('expire','терять силу, истекать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('inspire','вдохновлять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('proficiency','умение, опытность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('arcana','тайны');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('insight','понимание');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('intimidate','запугивать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('investigate','расследовать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('perception','восприятие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('persuasion','убеждение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sleight of hand','ловкость рук');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('stealth','хитрость');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('affinity','сродство, близость');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ferret','выискивать, хорек');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('due','должное, в связи');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('carapace','хитиновый панцирь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('blister','волдырь, пузырь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('former','бывший');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('recepient','получатель');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('towel','полотенце');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('broom','веник');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pan','кастрюля');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hose','шланг');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('attitude','отношение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('dispatch','отправка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('persuasive','убедительный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('suspect','подозревать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('accustomed','привыкший');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('disaster','катастрофа, бедствие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('get used to','привыкать к');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('embed','встраивать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('awful','страшный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('assure','убеждать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('radiance','сияние');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('insufferable','невыносимый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('purpose','цель');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('plot','сюжет, заговор, график, чертеж');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('scale','масштаб, шкала');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('estimate','оценка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('exact, accurate, precise','точный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rural','сельский');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('divide','делить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('obtain','получать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('at the same time','заодно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hinder','мешать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('versatile','разносторонний, гибкий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('foe','противник');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('survey','опрос, обозрение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('lane','дорожная полоса, переулок');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('substitution','замена');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('how many of you','как много вас');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('approximately','примерно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rude','грубый, резкий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('поочереди','one by one, in rotation');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('premise','предпосылка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('amusement','развлечение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('assess','оценить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('legislation','законодательство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('negotinations','переговоры');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('necessary','необходимый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('measure','мера');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('intent','намерение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('familiar','знакомый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sole','подошва, единственный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('obligatory, mandatory','обязательный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('consent','согласие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('absence, lack','отсутствие, недостача');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('valuable','ценный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bid','заявка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rely','полагаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('commitment','обязательство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sign up','подписаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('comply','соблюдать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('as for','что касается');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('kickoff','начало');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('itemized','детализированный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('loan','заем');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('carry','нести');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('suggest','предлагать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('seldom','редко');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('by the time','ко времени');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('capable','способный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('approach','подход');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('extensive','обширный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('adopt','принять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('robust','крепкий, здравый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('thorough','подробный, тщательный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('poll','голосование');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('clarify','прояснить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('currently','в настоящее время');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('as far as i understand','насколько я понимаю');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('permit','разрешать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('excess','избыток, лишний');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('threshold','предел');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('distract','отвлекать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('divine','божественный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('reflection','отражение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('furnace','печь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('morass','трясина');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('lullaby','колыбельная');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('obliteration','уничтожение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bliss','блаженство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('phenomenon','явление');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('attend','присутствовать, посещать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('gather','собирать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('boast','хвастать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('quarrel','ссориться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hassle','стычка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('lash','плеть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('purge','чистка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('credible','заслуживающий доверия');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('chock up','загромождать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('errand','поручение, командировка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('agenda','повестка дня');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pronunciation','произношение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('lean','опираться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('entire','весь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('trade-off','компромисс');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('take into account','принять к сведению');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('meadow','луг');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hike','поход, взбираться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('obscure','безвестный, смутный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('shatter','поколебать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('alias','псевдоним');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('convenient','удобный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('figure out','разобраться с');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('eager','нетерпеливый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('dedication','самоотверженность, преданность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('diligence','усердие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('perseverance','упорство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rowing','гребля');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('consistently','последовательно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('able','в состоянии');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rough','грубый, черновой');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fold','складка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('perceive','воспринимать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('deaf','глухой');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('impair','ухудшать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('magnifier','лупа');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('refugee','беженец');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('enhance','усиливать, совершенствовать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('opportunity','возможность, случай');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('possibility','возможность, вероятность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('according','в соответствии');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('behalf','от имени');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('although','хотя');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('essential','существенный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('convey','передавать, сообщать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rather','скорее, вернее, довольно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('comprehensible','понятный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('adjust','регулировать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bypass','обход');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('preserve','сохранять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('obsolete','устаревший');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('cluttered','загроможденный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('predict','предсказывать, прогнозировать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('appropriate','соответствующий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('awareness','осведомленность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('reliably','надежно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('famine','голод');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('determination','определение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('revenue','доход');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ancestor','предок');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('descendant','потомок');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sibling','родной брат');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('dare','сметь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('contradict','противоречить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rummage','рыться, копаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('nonetheless','тем не менее');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('by no means','ни в коем случае');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('vigilant','бдительный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('cross out','вычеркнуть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('gratitude','благодарность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bestow','даровать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('cozy','уютный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('urgent','срочный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('up to you','на твое усмотрение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('definitely','определенно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('arrange','организовывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('marvelous','чудесный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('look forward','ждать с нетерпением');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('stick','придерживаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ambient','окружающий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('stash','копить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('exceed','превышать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('duration','продолжительность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('precept','заповедь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('behavior','поведение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('harm','вред');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('regarding','относительно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('precision','точность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('handle','справляться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fluent','беглый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('instead','вместо');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('vouch','ручаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('authenticity','подлинность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('instantiate','иллюстрировать примерами');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('appoint','назначать встречу');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('take offense','обижаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('vendor','продавец');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bend','изгиб, гнуть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('glance','беглый взгляд');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('glow','пылать, жар, свечение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('shaft','стержень, ось, вал');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('drool','сочиться, течь, пороть чушь, млеть от счастья, пускать слюни');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fondle','ласкать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fuzzy','нечеткий, ворсистый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('snout','морда, рыло');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('squirm','извиваться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('wiggle','покачиваться, ерзать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('tip','кончик');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('vicinity','окрестности');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pervert','извращенец');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('nuzzle','прижаться, уткнуться носом');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pounce','внезапно атаковать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bulge','выпуклость, выступ');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rub','тереться, тереть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('wag','вилять, махать, взмах, шутник');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('distinct','отчетливый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('overlap','накладываться, перекрывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('in a way','в некотором отношении');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('mastermind','руководить, управлять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('tense','напряженный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('gratification','удовлетворение, вознаграждение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sue','предъявлять иск, возбуждать дело');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('household','домашнее хозяйство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('literal','буквальный, дословный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('argent','серебристость');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('species','вид, порода');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('luggage','багаж');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('slight','легкий, незначительный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('inebriate','алкоголик');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hangover','похмелье');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('patient','терпеливый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('slippery','скользкий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('slope','склон');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('likewise','аналогично');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('flourish','процветать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('throughout','на протяжении');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('deteriorate','ухудшаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('prone','склонный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('make sense','иметь смысл');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('beforehand','заранее');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('recognize','распознать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('infer','делать вывод');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('caption','подпись');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('mention','упомянуть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('cuisine','кухня, стряпня');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('weird','странный, непонятный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('overall','в общем и целом');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fine','штрафовать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('frustrate','срывать планы');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('shy','застенчивый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('empathetic','чуткий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('generous','щедрый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('compassion','сострадание');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('attractive','привлекательный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('odds','шансы');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('frankly','откровенно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('nitpick','придираться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('grumbler','ворчун');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('disheveled','взъерошенный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('effort','усилие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('treat','угощать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('steer','управлять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('puddle','лужа');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('in demand','востребован');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fraud','мошенничество');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('far-fetched','надуманный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fee','плата');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('voluntarily','добровольно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('witty','остроумный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('neat','аккуратный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bribe','взятка, давать взятку');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('biased','необъективный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('abroad','за рубежом');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('carried away','увлекся');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pivot','стержень');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('blink, twinkle','мигать, мерцать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hesitate','стесняться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('insist','настаивать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('precedence','старшинство, первенство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('nested','вложенный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('participate','участвовать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('postpone','откладывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('mess','беспорядок');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('attentive','внимательный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('intently','пристально');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('disgusting','отвратительный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('find out','выяснить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('burst','взрыв, вспышка, лопнуть, разрывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sprinkle','посыпать, брызгать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('straw','солома');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('log','бревно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('high time','пора');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('poke','тыкать, совать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('appreciation','признательность, высокая оценка, уважение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('recently','недавно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('concern','касаться, относиться к');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('out of curiosity','чисто из любопытства');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('out of','сделанный из');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('plausibly','правдоподобно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('stroll','прогулка, гулять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('impression','впечатление');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('regardless, despite','несмотря на');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('salmon','лосось');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('excruciating, tormenting','мучительно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('misuse','злоупотреблять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('imply','подразумевать, означать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('indent','отступ');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('notch','паз, выемка, зубец');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('regret','сожалеть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('remorse','раскаяние');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pending','в ожидании');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('circumstance','обстоятельство');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('wallow','валяться, барахтаться, купаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('perverse','извращенный, ошибочный, упрямый, неправильный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('diversity','разнообразие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('revoke','аннулировать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('whim','каприз');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('contemptuous','презрительный, высокомерный, пренебрежительный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('despise, scorn','презирать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('affect','влиять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('proceed','продолжать, действовать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('resort','курорт');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('familiarize','ознакомиться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('as a matter of fact','на самом деле');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('rob, plunder','грабить, разбойничать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('put out','выводить из себя');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('oppress','угнетать, притеснять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('relevant','актуальный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('distinguish','различать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('schedule','расписание');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('collaborate','сотрудничать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('retire','уходить в отставку, изымать из использования');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('insurance','страховка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('belay','страховать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('disease','болезнь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('justify','оправдывать, обосновывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('deed','поступок');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('accuse','обвинять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('conspire','сговариваться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('abduct','похищать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('heir','наследник, преемник');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ridge','горный хребет');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sewer','сточная труба, коллектор');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('slug','слизень');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('interrogate','допрашивать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('muffle','заглушать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('torture','пытать, пытка');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('utmost','все возможное');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('conduct','проводить, поведение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('inclination','наклонность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('cliff','утес');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('elaborate','разрабатывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('leak','утечка, просачиваться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('surreptitious','подпольный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('penultimate','предпоследний');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('respectively','соответственно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('foresee','предвидеть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hedge','живая изгородь');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('preface','предисловие');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('willingly','охотно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('endorse','одобрять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('gain, obtain, receive, fetch','получать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('allow','допускать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('achieve','достигнуть, добиться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('long ago','давно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('abruptly','резко');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('annoy','раздражать, досаждать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bother','наскучивать, надоедать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('covenant','завет, соглашение, пакт');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('staff','персонал, посох');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('stuff','материал, вещи, имущество');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('keep an eye','приглядеть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('embarrass','смущать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('conscience','совесть');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('whose','чей');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('whenever','когда угодно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('admire','восхищаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('confidence','уверенность');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('decent','порядочный, приличный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('batch','партия, пакет, серия');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('bunch','гроздь, группа, стадо');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pale','бледный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('faint','слабо заметный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('saturate','насыщать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('hence','следовательно');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('fence','забор, ограда');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('I dont mind','я не против');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ambiguous','двусмысленный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('dangle','болтаться, свисать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('ambiguous','двусмысленный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('kink','причуда, изгиб, чудить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('pat','трепать, похлопывать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('cherish','лелеять');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('possess','обладать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('complain','жаловаться');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('compatible','совместимый');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('favorable','благоприятный');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('praise','похвала');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('patience','терпение');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('awkward','неловкий');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sew','шить');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('spit','плевать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('soothe','успокаивать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('claim','требовать, иск');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('crop, clip','обрезать');\n" +
            "INSERT INTO $TABLE_NAME ($WORD, $TRANSLATION ) VALUES ('sue','иск, предъявлять иск');"