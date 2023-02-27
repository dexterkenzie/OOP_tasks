// 1. Реализовать, с учетом ооп подхода, приложение Для проведения исследований с генеалогическим древом. 
// Идея: описать некоторое количество компонент, например: модель человека компонента хранения связей и отношений между людьми: 
// родитель, ребёнок - классика, но можно подумать и про отношение, брат, свекровь, сестра и т. д. 
// компонент для проведения исследований дополнительные компоненты, например отвечающие за вывод данных в консоль, 
// загрузку и сохранения в файл, получение\построение отдельных моделей человека Под “проведением исследования” можно понимать получение 
// всех детей выбранного человека. Описать с ООП стиле, логику взаимодействия объектов реального мира между собой: шкаф-человек. 
// Какие члены должны быть у каждого из классов (у меня на семинаре студенты пришли к тому, чтобы продумать логику взаимодействия 
// жена разрешает открыть дверцу шкафа мужу, после чего эту дверцу можно открыть)


public class FamilyTree { 
        
        static int N = 8; 
    
        static class Node
        {
            char val;
            Node[] child = new Node[N];
    
            Node(char Pip)
            {
                val = Pip;
                for (int i = 0; i < N; i++)
                    child[i] = null;
            }
        }
        
        static void printChild(Node root, char Pip, int chain) 
        {            
            if (root.val == Pip)
            {
                if (root.child[chain - 1] == null)
                    System.out.print("Ошибка: Данных не существует \n");
                else
                    System.out.print(root.child[chain - 1].val + "\n");
            }
    
            for (int i = 0; i < N; i++)
                if (root.child[i] != null)
                    printChild(root.child[i], Pip, chain);
        }
    
        public static void main(String[] args)
        {
            Node root = new Node('A');
            root.child[0] = new Node('B');
            root.child[1] = new Node('C');
            root.child[2] = new Node('D');
            root.child[3] = new Node('E');
            root.child[0].child[0] = new Node('F');
            root.child[0].child[1] = new Node('G');
            root.child[2].child[0] = new Node('H');
            root.child[0].child[0].child[0] = new Node('I');
            root.child[0].child[0].child[1] = new Node('J');
            root.child[0].child[0].child[2] = new Node('K');
            root.child[2].child[0].child[0] = new Node('L');
            root.child[2].child[0].child[1] = new Node('M');

            char Pip = 'F';
            System.out.print("Второй ребёнок F: ");
            printChild(root, Pip, 2);

            Pip = 'A';
            System.out.print("Седьмой ребёнок А: ");
            printChild(root, Pip, 7);
        }
    }