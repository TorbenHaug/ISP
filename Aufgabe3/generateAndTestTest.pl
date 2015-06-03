% Autor:
% Datum: 03.06.2015

:-begin_tests(generate).

  test(generate) :- assertion(generate([[color(_),nationality(_),pet(_),drink(_),cigarette(_)],
                                    [color(_),nationality(_),pet(_),drink(_),cigarette(_)],
                                    [color(_),nationality(_),pet(_),drink(_),cigarette(_)],
                                    [color(_),nationality(_),pet(_),drink(_),cigarette(_)],
                                    [color(_),nationality(_),pet(_),drink(_),cigarette(_)]])).

:-end_tests(generate).


:-begin_tests(test).

   test(test) :- assertion(einsteintest([[color(yellow),nationality(norway),pet(fox),drink(water),cigarette(kools)],
                                         [color(blue),nationality(ukraine),pet(horse),drink(tea),cigarette(chesterfield)],
                                         [color(red),nationality(english),pet(snake),drink(milk),cigarette(oldgold)],
                                         [color(ivory),nationality(spanish),pet(dog),drink(orangejuice),cigarette(luckystrike)],
                                         [color(green),nationality(japan),pet(zebra),drink(coffee),cigarette(parliament)]])).
                                         
   test(test) :- assertion(not(einsteintest([[color(blue),nationality(norway),pet(fox),drink(water),cigarette(kools)],
                                         [color(yellow),nationality(ukraine),pet(horse),drink(tea),cigarette(chesterfield)],
                                         [color(red),nationality(english),pet(snake),drink(milk),cigarette(oldgold)],
                                         [color(ivory),nationality(spanish),pet(dog),drink(orangejuice),cigarette(luckystrike)],
                                         [color(green),nationality(japan),pet(zebra),drink(coffee),cigarette(parliament)]]))).

einsteintest:-end_tests(test).