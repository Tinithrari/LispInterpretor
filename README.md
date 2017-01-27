# Projet interpréteur lisp

La page [Lisp sur wikipedia](https://en.wikipedia.org/wiki/Lisp_%28programming_language%29) et la page [Scheme sur wikipedia](https://en.wikipedia.org/wiki/Scheme_%28programming_language%29) fournissent l'historique et les fonctionnalités du langage.

Le comportement de l'interpréteur (le tests) sont validés à l'aide de l'interpréteur [JScheme](http://jscheme.sourceforge.net/jscheme/main.html).

Le nombre de tests augmentera au cours du semestre jusqu'à obtenir un interpréteur pleinement fonctionnelle.

Ce projet est basé sur le travail de [Peter Norvig en Python](http://norvig.com/lispy.html).

Peter Norvig a aussi proposé une version [Java de son interpréteur](http://norvig.com/jscheme.html). Cette version a été conçue à la naissance du langage, et ne peut pas être considérée comme une conception oriénté objet d'un interpréteur Lisp.

Plus récemment, [une version Java a été proposée par Remy Forax](https://forax.github.io/2014-06-01-e733e6af6114eff55149-lispy_in_java.html). 
Si cette solution utilise des concepts avancés et récents de Java (lambdas et streams), le code reste proche de la version python, donc pas vraiment orientée objet.

## Pour déclarer ce projet comme source `upstream`

Il suffit de déclarer une fois ce projet comme dépôt distant de votre fork :

```
$ git remote add upstream https://forge.univ-artois.fr/m1-2016-2017/TDD2017IMPL.git
```

Ensuite, à chaque mise à jour de ce projet, vous pouvez mettre à jour votre fork
à l'aide des commandes suivantes :

```
$ git pull upstream master
$ git push
```

## Si vous ne voyez plus vos tests dans votre projet

Il suffit de rajouter le projet de tests comme un sous module du projet :

```
$ git submodule add https://forge.univ-artois.fr/m1-2016-2017/TDD2017TESTS.git
$ git commit -m "Les tests sont de retour"
$ git push
```

