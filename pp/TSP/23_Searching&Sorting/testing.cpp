#include<iostream>
using namespace std;
#include<vector>


class DoublyLinkedList {
    public : class Node {
        public:
      int data = 0;
      Node* prev = nullptr;
      Node* next = nullptr;

          Node(int data) {
        this->data = data;
      }

    };

    Node* head = nullptr;
    Node* tail = nullptr;
    int size = 0;

    string toString() {
      string sb;
      Node* curr = this->head;
      sb += "[";
      while (curr != nullptr) {
        sb += (to_string)(curr->data);
        if (curr->next != nullptr)
          sb+=", ";
        curr = curr->next;
      }
      sb += "]";

      return sb;
    }

    // Exceptions========================================

    private: bool indexIsInvalidException(int index, int leftRange, int rightRange) {
      if (index < leftRange || index > rightRange) {
        cout << ("IndexIsInValid: ");
        return true;
      }
      return false;
    }


    private:bool ListIsEmptyException() {
      if (this->size == 0) {
        cout << "ListIsEmpty: ";
        return true;
      }
      return false;
    }

    // BasicFunctions======================================

    public:int Size() {
        // write your code here
        return size;
    }

    public:bool isEmpty() {
       // write your code here

       return size==0;
       
    }

    // AddFunctions======================================

    public:void addFirstNode(Node* node) {
      if (this->size == 0)
        this->head = this->tail = node;
      else {
        node->next = this->head;
        this->head->prev = node;
        this->head = node;
      }
      this->size++;
    }

    public: void addFirst(int val) {
      Node* node = new Node(val);
      addFirstNode(node);
    }

    public:void addLastNode(Node* node) {
      if (this->size == 0)
        this->head = this->tail = node;
      else {
        this->tail->next = node;
        node->prev = this->tail;
        this->tail = node;
      }
      this->size++;
    }

    public:void addLast(int val) {
      Node* node = new Node(val);
      addLastNode(node);
    }

    // RemoveFunctions======================================

    public:Node *removeFirstNode() {
      Node* node = this->head;
      if (this->size == 1)
        this->head = this->tail = nullptr;
      else {
        Node* nextNode = this->head->next;
        nextNode->prev = nullptr;
        node->next = nullptr;

        this->head = nextNode;
      }

      this->size--;
      return node;
    }

    public:int removeFirst() {
      if (ListIsEmptyException())
        return -1;
      Node* node = removeFirstNode();
      return node->data;
    }

    public:Node* removeLastNode() {
      Node* node = this->tail;
      if (this->size == 1)
        this->head = this->tail = nullptr;
      else {
        Node* prevNode = this->tail->prev;
        prevNode->next = nullptr;
        node->prev = nullptr;

        this->tail = prevNode;
      }

      this->size--;
      return node;
    }

   public:int removeLast() {
      if (ListIsEmptyException())
        return -1;
      Node* node = removeLastNode();
      return node->data;
    }

    // getFunctions======================================

    public:int getFirst() {
        if(ListIsEmptyException()){
            return -1;
        }
        return head->data;
        
    }
    private: Node* getNodeAt(int index) {
      Node*curr = this->head;
      while (curr != NULL && index-- > 0)
        curr = curr->next;

      return curr;
    }
    public:int getLast() {
        //write your code here
        if(ListIsEmptyException()){
            return -1;
        }
        return tail->data;
    }

    private: void addNodeAt(int index, Node* node) {
      if (index == 0)
        addFirstNode(node);
      else if (index == this->size)
        addLastNode(node);
      else {
        Node* forw = getNodeAt(index);
        Node* prev = forw->prev;

        prev->next = node;
        node->prev = prev;

        node->next = forw;
        forw->prev = node;

        this->size++;
      }
    }
    public : int getAt(int index){
       if(ListIsEmptyException()){
           return -1;
       }else if(indexIsInvalidException(index , 0, size-1)){
           return -1;
       }
       
       Node* curr = head;
       for(int i=1 ; i <= index ; i++){
           curr = curr->next;
       }
       
       return curr->data;
       
    }
    public:void addAt(int index, int data) {
      if (indexIsInvalidException(index, 0, this->size))
        cout << -1 << endl;
      else {
        Node* node = new Node(data);
        addNodeAt(index, node);
      }
    }
    private: Node* removeAtNode(int index) {
      if (index == 0)
        return removeFirstNode();
      else if (index == this->size - 1)
        return removeLastNode();
      else {
        Node* node = getNodeAt(index);
        Node* prev = node->prev;
        Node* forw = node->next;

        prev->next = forw;
        forw->prev = prev;

        node->next = nullptr;
        node->prev = nullptr;

        this->size--;
        return node;
      }
    }

    public: int removeAt(int index) {
      if (ListIsEmptyException())
        return -1;

      Node* node = removeAtNode(index);
      return node->data;
    }
    public :void addBefore(Node* refNode, int data) {
      Node* node = new Node(data);
      Node* prev = refNode->prev;

      if (prev == nullptr) {
        node->next = refNode;
        refNode->prev = node;
        this->head = node;
      } else {
        prev->next = node;
        node->prev = prev;

        node->next = refNode;
        refNode->prev = node;
      }

      this->size++;
    }

    public: void addBefore(int idx, int data) {
      Node* node = getNodeAt(idx);
      addBefore(node, data);
    }

    public :void addAfter(Node* refNode, int data) {
      Node* node = new Node(data);
      Node* forw = refNode->next;

      if (forw == nullptr) {
        refNode->next = node;
        node->prev = refNode;
        this->tail = node;
      } else {
        forw->prev = node;
        node->next = forw;

        node->prev = refNode;
        refNode->next = node;
      }

      this->size++;
    }

    public: void addAfter(int idx, int data) {
      Node* node = getNodeAt(idx);
      addAfter(node, data);
    }
    
    private: Node *removeAfterNode(Node* refNode) {
      Node* forw = refNode->next;
      if (forw->next == nullptr) {
        refNode->next = nullptr;
        forw->prev = nullptr;
        this->tail = refNode;
      } else {
        refNode->next = forw->next;
        forw->next->prev = refNode;

        forw->next = nullptr;
        forw->prev = nullptr;
      }
      this->size--;
      return forw;
    }

    public: int removeAfter(Node* refNode) {
      if (refNode->next == nullptr) {
        cout << ("LocationIsInvalid: ") << endl;
        return -1;
      }
      return removeAfterNode(refNode)->data;
    }

    public :int removeAfter(int idx) {
      Node* node = getNodeAt(idx);
      return removeAfter(node);
    }

    private: Node* removeBeforeNode(Node* refNode) {
      Node* prevNode = refNode->prev;
      if (prevNode->prev == nullptr) {
        refNode->prev = nullptr;
        prevNode->next = nullptr;
        this->head = refNode;
      } else {
        refNode->prev = prevNode->prev;
        prevNode->prev->next = refNode;

        prevNode->next = nullptr;
        prevNode->prev = nullptr;
      }
      this->size--;
      return prevNode;
    }

    public :int removeBefore(Node* refNode) {
      if (refNode->prev == nullptr) {
        cout << "LocationIsInvalid: " << endl;
        return -1;
      }
      return removeBeforeNode(refNode)->data;
    }

    public: int removeBefore(int idx) {
      Node* node = getNodeAt(idx);
      return removeBefore(node);
    }

    public: int removeNode(Node* refNode) {
      Node* prev = refNode->prev;
      Node* forw = refNode->next;
      if (this->size == 1)
        this->head = this->tail = nullptr;
      else if (prev == nullptr)
        this->head = forw;
      else if (forw == nullptr)
        this->tail = prev;
      else {
        prev->next = forw;
        forw->prev = prev;
      }

      refNode->prev = refNode->next = this->head->prev = this->tail->next = nullptr;
      this->size--;
      return refNode->data;
    }

    public: int removeNode(int idx) {
      
    //   Node* node = getNodeAt(idx);
    //   return removeBefore(node);
    return 0;
    }

    public :void displayForw(){
        cout << "[";
        Node* curr = head;
        while(curr != nullptr){
            cout << curr->data;
            if(curr->next != nullptr){
                cout<<", ";
            }
            curr = curr->next;
        }
        cout <<"]"<<endl;
    }
    public :void displayBack(){
       cout << "[";
        Node* curr = tail;
        while(curr != nullptr){
            cout << curr->data;
            if(curr->prev != nullptr){
                cout<<", ";
            }
            curr = curr->prev;
        }
        cout <<"]"<<endl;
    }


  };
    

  int main() {
    DoublyLinkedList *dll = new DoublyLinkedList();
    
    string str;
    cin >> str;
    
    while(str != "stop"){
        int data;
          if(str == ("addFirst")){
              cin >> data;
            dll->addFirst(data);
          }
          else if (str == ("addLast")){
              cin >> data;
              dll->addLast((data));
          }
          else if (str == ("removeFirst"))
            cout << (dll->removeFirst()) << endl;
          else if (str == ("removeLast"))
            cout << (dll->removeLast()) << endl;
          else if (str == ("getFirst"))
            cout << (dll->getFirst()) << endl;
          else if (str == ("getLast"))
            cout << (dll->getLast()) << endl;
          else if (str == ("size"))
            cout << (dll->Size()) << endl;
          else if (str == ("isEmpty"))
            cout << (dll->isEmpty() == 0 ? "false" : "true") << endl;
          else if(str == ("getAt")){
              cin >> data;
              cout << dll->getAt(data) << endl ;
          }
          else if (str == ("addAt")){
              cin >> data;
              int val;
              cin >> val;
            dll->addAt(data , val);
          }
          else if (str == ("removeAt")){
              cin >> data;
            dll->removeAt(data);
          }
          else if (str == ("addBefore")){
              cin >> data;
              int val;
              cin >> val;
            dll->addBefore(data , val);
          }
          else if (str == ("addAfter")){
              cin >> data;
              int val;
              cin >> val;
            dll->addAfter(data, val);
          }
          else if (str == ("removeAfter")){
              cin >> data;
            cout << (dll->removeAfter(data)) << endl;
          }
          else if (str == ("removeBefore")){
              cin >> data;
            cout << (dll->removeBefore(data)) << endl;
          }
          else if (str == ("removeNode")){
              cin >> data;
            cout << (dll->removeNode(data)) << endl;
          }
          else if (str == ("displayForw")){
            dll->displayForw();
          }
          else if (str == ("displayBack")){
            dll->displayBack();
          }


        cin >> str;
    }
    cout << dll->toString();
    
    return 0;   
 }