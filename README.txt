Dumitrescu Andrei 323CC
Grad de dificultate: Mediu
Timp acordat: Vreo 2 saptamani adunate. (mult timp pierdut pe interfetele
grafice care arata naspa, deoarece design-ul nu e placerea si atributia mea).

Detalii din implementare mai importante sau mai greu de inteles:

1. ApplicationController - Controller care reprezitna interfata initiala
si care se ocupa de legatura intre cele 3 interfete.

2. ReadController - Aici se afla functionalitatea citirii din fisiere.

3. Exceptii noi pentru situatii cum ar fi atunci cand nu este niciun
recruiter in companie.

4. Recruiter getRecruiter(User user) da throw la exceptia NoRecruiter

5. Company extinde Subject pentru Observer Pattern

6. int getDegreeInFriendship(Consumer consumer) este un BFS cu HashSet
pentru a detecta prietenii deja vizitati.

7. Integer getGraduationYear() - Am considerat prima educatie din lista
sortata ca fiind buna. Daca nu are data de sfarsit dau throw la o eroare.

8. Double meanGPA() throw InvalidGPAException - in caz ca nu are studii

9. Job findJobByName(String name) - caut job dupa nume in departament

10. in EMployee am un constructor de copiere pentru a face conversia
de la User la Employee mai usor.

11. void setOpened(boolean isOpened) - setez valabilitatea jobului si
notific toti observers despre noua schimbare.

12. void process(Job job) - Parcurg requesturile si verific daca este
valid si este pentru jobul curent. De asemenea verific daca jobul mai
este valid. Procesez requestul si fac modificarile necesare.

13. AdminPanel - Folosesc JTree pentru a afisa departamentele si
angajatii din fiecare companie.

14. Consumer getConsumerByEmail(String email) - Caut consumer dupa email/

15. static Department getIfDepartmentExists(String departmentName,
ArrayList<Department> departments) - verific daca un departament exista
intr-o lista de departamente.

16. In clasa Test citesc toate informatiile din fisier, fac ca fiecare user
sa aplice la toate joburile de la companiile la care sunt interesati. Apoi
afisez informatii despre aplicatie.

17. In TryApp rulez main-ul din test si dupa pornesc interfata grafica.

#####################################
BONUSURI:

1. Prototype pattern pentru a copia obiecte ( Xerox class )

2. PairArray - arraylist de perechi care permite cautarea dupa key.

3. Un fel de Mediator Pattern ( implementat pe baza observer pattern ) 
pentru a face comunicarea intre interfete atunci cand se schimba ceva la una.
De exemplu: Dupa ce se accepta un user intr-o firma, se sterge din lista de
de users din admin panel si se updateaza Tree-ul firmei respective.

4. O singura interfata legata cu ajutorul CardLayout pentru a naviga usor
intre interfetele aplicatiei.

5. JTable cu butoane utilizabile in ele.

6. Singleton thread safe folosind sincronizarea.

P.S.: TryApp ruleaza Test si apoi porneste interfata. Mentionez ca interfata
initiala porneste special pt o companie din cod. Nu am avut timp sa
implementez un sistem de login pentru a schimba asta.



