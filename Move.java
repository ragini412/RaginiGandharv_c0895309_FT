import java.util.ArrayList;
import java.util.List;

class Move {
    private List<Box> boxes;

    public Move(int numBoxes) {
        this.boxes = new ArrayList<>(numBoxes);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public void print() {
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            if (box != null) {
                box.displayContents();
            }
        }
    }

    public int find(String itemName) {
        for (Box box : boxes) {
            if (box != null) {
                int result = box.findBoxByItem(itemName);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Move move = new Move(2);

        Box box1 = new Box(1, 1);
        box1.addItem(new SimpleItem("scissors"));

        Box box2 = new Box(1, 2);
        box2.addItem(new SimpleItem("book"));

        Box box3 = new Box(2, 3);
        box3.addItem(new SimpleItem("compass"));
        Box box4 = new Box(1, 4);
        box4.addItem(new SimpleItem("scarf"));
        box3.addItem(new BoxItem(box4));

        Box box5 = new Box(3, 5);
        box5.addItem(new BoxItem(box1));
        box5.addItem(new BoxItem(box2));
        box5.addItem(new BoxItem(box3));

        Box box6 = new Box(3, 6);
        box6.addItem(new SimpleItem("pencils"));
        box6.addItem(new SimpleItem("pens"));
        box6.addItem(new SimpleItem("rubber"));

        move.addBox(box5);
        move.addBox(box6);

        move.print();

        System.out.println("The scarf is in the cardboard number " + move.find("scarf"));
    }
}

class Box {
    private List<Item> items;
    private int boxNumber;

    public Box(int capacity, int boxNumber) {
        this.items = new ArrayList<>(capacity);
        this.boxNumber = boxNumber;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void displayContents() {
        System.out.print("Cardboard " + boxNumber + " contains: ");
        for (Item item : items) {
            if (item != null) {
                item.display();
            }
        }
        System.out.println();
    }

    public int findBoxByItem(String itemName) {
        for (Item item : items) {
            if (item != null && item.getName() != null && item.getName().equals(itemName)) {
                return boxNumber;
            }
            if (item instanceof BoxItem) {
                BoxItem boxItem = (BoxItem) item;
                int result = boxItem.getBox().findBoxByItem(itemName);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }
}

class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.print(getName() + " ");
    }
}

class BoxItem extends Item {
    private Box box;

    public BoxItem(Box box) {
        super(null);
        this.box = box;
    }

    public Box getBox() {
        return box;
    }

    @Override
    public void display() {
        box.displayContents();
    }
}


class SimpleItem extends Item {
    public SimpleItem(String name) {
        super(name);
    }
}


