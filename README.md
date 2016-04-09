# Application
MBPL's application to show some graphical passwords.

Pierre : 
J'ai fait l'ecran d'accueil avec 3 tabs ( on les appelle des fragments en fait )
1er tab : toutes les icones des mdp, si on clique dessus, ça nous y amène directement
2em tab : les données mises à jour après chaque essai de mdp
3em tab : les données du TER à ajouter
Les 3 classes commencant par Passpoints_ concernent le mot de passe passpoints uniquement
La page Accueil utilise les classes TabFragment1/2/3 qui contiennent les données dans chaque fragment,
  la clasee TypeAuthentification sert à pouvoir trier les types de mdp
  la classe PagerAdapter sert à agencer les tabs entre eux par rapport à la page Accueil

Pour l'instant l'application est fonctionnelle 09/04/2016.
