package modelo;

import java.util.ArrayList;

/**
 *
 * @author guill
 */
public interface DAO<E, I> {

    public int add(E bean);

    public int delete(I id);

    public int update(E bean);

    public ArrayList<E> findAll(E bean);
}
