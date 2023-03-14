# Bank account kata :bank: :dollar:

### What are we talking about?

The original kata proposal can be found [here on kata-log](https://kata-log.rocks/banking-kata).


### The design intent

I've helped engineer software for a banking company .

I tried to comply with both KISS & YAGNI clean code principles.

In the meantime, I've tried to flex my memory muscles a bit and implement a less naive domain than required.

> In a nutshell, my aim was to give a sense of reality to this kata and set decent foundations capable of evolving with modern banking business rules and standards.


### BDD is here!

We're given a few scenarios (_scenarii_ for the more distinguished) right away with this kata.

I thought they would fit perfectly to try and use **Cucumber** on this project.

You can find every cucumber test feature under `bankaccount-kata/src/test/resources/cucumber/`


### Design choices

Went full TDD on it, so it's far from perfect as I'm still learning.

Went for Java's BigDecimal instead of primitive int for the reason I listed above (a bit more real).

For now neither Layered or Ports and Adapter architectures are respected. I'll fix it asap.


### How to try it

You should be able to launch the tests once the repo is cloned on your machine with the following command :

`./mvnw test`

Feel free to add any in order to try the app's behavior!