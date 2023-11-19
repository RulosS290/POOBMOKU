package domain;

import java.io.*;

public class poobMokuGame {
    private int players;
    private player player1;
    private player player2;

    public poobMokuGame(){
    }

    public poobMokuGame open(File file) throws poobMokuException {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            poobMokuGame poobmokugame = (poobMokuGame) objectIn.readObject();
            objectIn.close();
            return poobmokugame;
        } catch (FileNotFoundException e) {
            throw new poobMokuException(poobMokuException.FILE_NOT_FOUND);
        } catch (StreamCorruptedException e) {
            throw new poobMokuException(poobMokuException.CORRUPT_FILE);
        } catch (InvalidClassException e) {
            throw new poobMokuException(poobMokuException.INVALID_CLASS);
        } catch (OptionalDataException e) {
            throw new poobMokuException(poobMokuException.PRIMITIVE_DATA_ERROR);
        } catch (IOException e) {
            throw new poobMokuException(poobMokuException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new poobMokuException(poobMokuException.OPEN_ERROR);
        }
    }

    public void save(File file) throws poobMokuException {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (FileNotFoundException e) {
            throw new poobMokuException(poobMokuException.FILE_NOT_FOUND);
        } catch (InvalidClassException e) {
            throw new poobMokuException(poobMokuException.INVALID_CLASS);
        } catch (NotSerializableException e) {
            throw new poobMokuException(poobMokuException.NOT_SERIALIZABLE);
        } catch (IOException e) {
            throw new poobMokuException(poobMokuException.INPUT_OUTPUT_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new poobMokuException(poobMokuException.SAVE_ERROR);
        }
    }
}
