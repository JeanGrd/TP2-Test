import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Une implémentation concrète de l'interface Stack utilisant une ArrayList.
 * Cette classe fournit une structure de données de type pile avec des opérations
 * de base telles que push, pop et peek.
 */
public class StackImpl implements Stack {
    private ArrayList<Double> stackList = new ArrayList<Double>();

    /**
     * Vérifie si la pile est vide.
     *
     * @return {@code true} si la pile est vide, sinon {@code false}.
     */
    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    /**
     * Retourne la taille de la pile.
     *
     * @return La taille de la pile.
     */
    @Override
    public int getSize() {
        return stackList.size();
    }

    /**
     * Ajoute un élément au sommet de la pile.
     *
     * @param item L'élément à ajouter au sommet de la pile.
     */
    @Override
    public void push(double item) {
        stackList.add(item);
    }

    /**
     * Retourne l'élément au sommet de la pile sans le retirer.
     *
     * @return L'élément au sommet de la pile.
     * @throws EmptyStackException Si la pile est vide.
     */
    @Override
    public double peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackList.get(stackList.size() - 1);
    }

    /**
     * Retire et retourne l'élément au sommet de la pile.
     *
     * @return L'élément au sommet de la pile.
     * @throws EmptyStackException Si la pile est vide.
     */
    @Override
    public double pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackList.remove(stackList.size() - 1);
    }
}
