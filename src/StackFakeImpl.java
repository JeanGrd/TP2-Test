import java.util.EmptyStackException;

/**
 * Une implémentation fictive de l'interface Stack.
 * Cette classe est conçue pour simuler le comportement d'une pile
 * dans des environnements de test, permettant de spécifier un état prédéfini
 * de la pile sans manipuler de véritables données.
 */
public class StackFakeImpl implements Stack {

    int size;
    boolean isEmpty;
    double topValue;

    /**
     * Constructeur pour créer une instance de StackFakeImpl.
     *
     * @param isEmpty Indique si la pile est vide ou non.
     * @param size La taille de la pile.
     * @param topValue La valeur au sommet de la pile.
     */
    public StackFakeImpl(boolean isEmpty, int size, double topValue) {
        this.isEmpty = isEmpty;
        this.size = size;
        this.topValue = topValue;
    }

    /**
     * Vérifie si la pile est vide.
     *
     * @return {@code true} si la pile est vide, sinon {@code false}.
     */
    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Retourne la taille de la pile.
     *
     * @return La taille de la pile.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Ajoute un élément au sommet de la pile.
     *
     * @param item L'élément à ajouter au sommet de la pile.
     */
    @Override
    public void push(double item) {
        topValue = item;
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
        return topValue;
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
        return topValue;
    }
}
