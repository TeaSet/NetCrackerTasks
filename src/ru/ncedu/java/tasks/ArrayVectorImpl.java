package ru.ncedu.java.tasks;

import java.util.Arrays;

/**
 * Created by Dmitriy on 07.10.2016.
 */
public class ArrayVectorImpl implements ArrayVector {

    private double[] array;

    @Override
    public void set(double... elements) {
        /**
         * Задает все элементы вектора (определяет длину вектора).
         * Передаваемый массив не клонируется.
         * @param elements Не равен null
         */
        this.array = Arrays.copyOf(elements, elements.length);
    }

    @Override
    public double[] get() {
        return this.array;
    }

    @Override
    public ArrayVector clone() {
        /**
         * Возвращает копию вектора (такую, изменение элементов
         *  в которой не приводит к изменению элементов данного вектора).<br/>
         * Рекомендуется вызвать метод clone() у самого массива или использовать
         *   {@link System#arraycopy(Object, int, Object, int, int)}.
         */
        ArrayVector arrClone = new ArrayVectorImpl();
        System.arraycopy(this.array, 0, arrClone, 0, this.array.length);
        return arrClone;
    }

    @Override
    public int getSize() {
        return get().length;
    }

    @Override
    public void set(int index, double value) {
        /**
         * Изменяет элемент по индексу.
         * @param index В случае выхода индекса за пределы массива:<br/>
         *  а) если index<0, ничего не происходит;<br/>
         *  б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
         */
        if (index < getSize())
            this.array[index] = value;
        else {
            double[] tmp = new double[index + 1];
            System.arraycopy(this.array, 0, tmp, 0, getSize());
            tmp[index] = value;
            array = tmp;
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        /**
         * Возвращает элемент по индексу.
         * @param index В случае выхода индекса за пределы массива
         *   должно генерироваться ArrayIndexOutOfBoundsException
         */
        if (index > getSize())
            throw new ArrayIndexOutOfBoundsException();
        else {
            /*
            for (index = 0; index < getSize(); index++)
                return array[index];
                */
            return get()[index];
        }
    }

    @Override
    public double getMax() {
        double max = array[0];
        for (int i = 0; i < getSize(); i++) {
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = array[0];
        for (int i = 0; i < getSize(); i++)
            if (get()[i] < min)
                min = get()[i];
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(get());
    }

    @Override
    public void mult(double factor) {
        /**
         * Умножает вектор на число.<br/>
         * Замечание: не пытайтесь использовать безиндексный цикл foreach:
         *  для изменения элемента массива нужно знать его индекс.
         * @param factor
         */
        for (int i = 0; i < getSize(); i++)
            get()[i] *= factor;
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        /**
         * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.<br/>
         * Если векторы имеют разный размер, последние элементы большего вектора не учитываются<br/>
         *  (если данный вектор - больший, его размер менять не надо, просто не меняйте последние элементы).
         * @param anotherVector Не равен null
         * @return Ссылка на себя (результат сложения)
         */
        double res;
        if (anotherVector.getSize() > this.getSize())
            res = this.getSize();
        else
            res = anotherVector.getSize();
        for (int i = 0; i < res; i++)
            get()[i] += anotherVector.get()[i];
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double res = 0.0, length;
        if (anotherVector.getSize() > this.getSize())
            length = this.getSize();
        else
            length = anotherVector.getSize();
        for (int i = 0; i < length; i++)
            res += this.get()[i] * anotherVector.get()[i];
        return res;
    }

    @Override
    public double getNorm() {
        return Math.sqrt(scalarMult(this));
    }
}
