/*
  Filename   : List.hpp
  Author     : Gary M. Zoppetti
  Course     : CSCI 362-01
  Assignment : N/A
  Description: List class, our implementation of a list ADT 
                 using a circular, doubly-linked list.
*/   
/************************************************************/
// Macro guard

#ifndef LIST_HPP
#define LIST_HPP

/************************************************************/
// System includes

#include <iostream>
#include <algorithm>
#include <cstddef>
#include <iterator>
#include <initializer_list>

/************************************************************/
// Local includes

/************************************************************/
// Using declarations

using std::ostream;
using std::ostream_iterator;
using std::initializer_list;

/************************************************************/

template <typename T>
struct ListNode 
{
  ListNode ()
    : data ()
  {
    // Do NOT modify the member initializer list above
    // Make a circular node, with next and prev
    //   pointing to this node
    next = this;
    prev = this;
  }

  // TODO
  ListNode (const T& d, ListNode* n, ListNode* p)
    : data (d), next(n), prev(n)
  // Initialize the three data members appropriately
  //   using a member initializer list 
  {
    next = n;
    prev = p;
  }

  T          data;
  ListNode*  next;
  ListNode*  prev;
};

/************************************************************/

template <typename T>
struct ListIterator
{
  using Self = ListIterator<T>;
  using Node = ListNode<T>;

  using difference_type = ptrdiff_t;
  using iterator_category = std::bidirectional_iterator_tag;

  using value_type = T;
  using pointer = T*;
  using reference = T&;

  ListIterator ()
    : m_nodePtr ()
  { }

  explicit
  ListIterator (Node* n)
    : m_nodePtr (n) 
  { }
  
  reference
  operator* () const
  { 
    return m_nodePtr->data;
  }
  
  // Return address of node's data member
  pointer
  operator-> () const
  { 
    return &m_nodePtr->data;
  }
  
  // Pre-increment
  Self&
  operator++ ()
  {
    Self temp(m_nodePtr);
    m_nodePtr = m_nodePtr->next;
    return temp;
  }
  
  // Post-increment
  Self
  operator++ (int)
  {
    Self temp (*this);
    m_nodePtr = m_nodePtr->next;
    return temp;
  }

  // Pre-decrement
  Self&
  operator-- ()
  {
    Self temp(m_nodePtr);
    m_nodePtr = m_nodePtr->prev;
    return temp;
  }
  
  // Post-decrement
  Self
  operator-- (int)
  {
    Self temp (*this);
    m_nodePtr = m_nodePtr->prev;
    return temp;
  }
  
  bool
  operator== (const Self& i) const
  {
    return m_nodePtr == i.m_nodePtr;
  }
  
  bool
  operator!= (const Self& i) const
  {
    return m_nodePtr != i.m_nodePtr;
  }
  
  /************************************************************/

  Node* m_nodePtr;

};

/************************************************************/

template <typename T>
struct ListConstIterator
{
  using Self = ListConstIterator<T>;
  using Node = const ListNode<T>;
  using iterator = ListIterator<T>;
  
  using difference_type = ptrdiff_t;
  using iterator_category = std::bidirectional_iterator_tag;

  using value_type = T;
  using pointer = const T*;
  using reference = const T&;
  
  ListConstIterator ()
    : m_nodePtr ()
  { }
  
  explicit
  ListConstIterator (Node* n)
    : m_nodePtr (n)
  { }
  
  ListConstIterator (const iterator& i)
    : m_nodePtr (i.m_nodePtr)
  { 
  }
  
  reference
  operator* () const
  { 
    return m_nodePtr->data;
  }
  
  pointer
  operator-> () const
  { 
    return &m_nodePtr->data;
  }

  Self&
  operator++ ()
  {
    Self temp (*this);
    m_nodePtr = m_nodePtr->next;
    return temp;
  }
  
  // Post-increment: TODO
  Self
  operator++ (int)
  {
    // Copy the iterator, make it point to the next node,
    //   and return the copied iterator
    Self copy (*this);
    m_nodePtr = m_nodePtr->next;
    return copy;
    }
  
  Self&
  operator-- ()
  {
    m_nodePtr = m_nodePtr->prev;
    return *this;
  }
  
  // Post-decrement: TODO
  Self
  operator-- (int)
  {
    // Analagous to post-increment
    Self copy (*this);
    m_nodePtr = m_nodePtr->prev;
    return copy;
  }
  
  bool
  operator== (const Self& i) const
  { 
    return m_nodePtr == i.m_nodePtr;
  }
  
  bool
  operator!= (const Self& i) const
  { 
    return m_nodePtr != i.m_nodePtr;
  }
  
  /************************************************************/

  Node* m_nodePtr;

};

template <typename T>
bool
operator== (const ListIterator<T>& i1,
	    const ListConstIterator<T>& i2)
{ 
  // Return whether the two iterators refer to the same node
  if (&(*i1) == &(*i2))
  {
    return true;
  }
  else
  {
    return false;
  }
  
}

template <typename T>
bool
operator!= (const ListIterator<T>& i1,
	    const ListConstIterator<T>& i2)
{ 
  // Return whether the two iterators refer to different nodes
  if (&(*i1) != &(*i2))
  {
    return true;
  }
  else
  {
    return false;
  }
  
}

/************************************************************/

template <typename T>
class List 
{
  using Node = ListNode<T>;

public:

  using iterator       = ListIterator<T>;
  using const_iterator = ListConstIterator<T>;

  using value_type      = T;
  using pointer         = T*;
  using reference       = T&;
  using const_reference = const T&;

  // TODO
  // Initialize an empty list
  List ()
  // Initialize data members using member initializer list
  : m_header (),
    m_size(0)
  {
    // Body should remain EMPTY
  }

  // Initialize a list with the elements in "values",
  //   in the same order
  // Called in cases like this:
  //    List<int> A { 1, 3, 7 };
  List (initializer_list<T> values)
    : m_header (),
      m_size (0)
  {
    // TODO
    // Add all values to this list (preserving order)
    Node* curr = &m_header;
    curr -> next = new Node(*values.begin(), nullptr, nullptr);
    Node* then = curr -> next;
    for(size_t i = 1; i <= values.size(); i++)
    {
      then -> next = new Node(*(values.begin()+i), nullptr, then);
      then = then -> next;
      m_size++;
    }
  }  

  // Initialize a list of size "n", with each value set to "defValue"
  explicit List (size_t n, const T& defValue = T ())
    : m_header(),
      m_size(n)
    {
      Node* curr = &m_header;
      curr -> next = new Node(defValue, nullptr, curr);
      Node* then = curr -> next;
      for(size_t i = n; i > 0; i--)
      {
        then -> next = new Node(defValue, nullptr, then);
        then = then -> next;
      }
    }

  // Copy constructor
  //   Initialize this list to be a copy of "otherList"
  List (const List& otherList)
  :m_header(),
   m_size(otherList.m_size) 
  {
      Node* curr = &m_header;
      Node* other = otherList.m_header.next;
      curr -> next = new Node(other->data, nullptr, curr);
      Node* then = curr -> next;
      for(size_t i = m_size; i > 0; i--)
      {
        other = other -> next;
        then -> next = new Node(other -> data, nullptr, then);
        then = then -> next;
      }
  }

  // Destructor
  ~List ()
  {
    // TODO
    // Remove all dynamically allocated nodes
  }

  // Assign "rhs" to this object
  //   Check for self-assignment using standard operator= pattern!
  List&
  operator= (const List& rhs)
  {
    if (this != &rhs)
    {
      return *this;
    }
    return *this;
  }

  // TODO
  // Return whether the list is empty
  bool    
  empty () const
  {
    if (m_size == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
    
  }
  
  // TODO
  // Return the size
  size_t
  size ()  const
  {
    return m_size;
  }

  // TODO
  // Return the first element
  reference
  front ()
  {
    return begin().m_nodePtr->data;
  }

  // TODO
  const_reference
  front () const
  {
    return begin().m_nodePtr->data;
  }

  // TODO
  // Return the last element
  reference
  back ()
  {
    return *(end()--);
  }

  // TODO
  const_reference
  back () const
  {
    return *(end()--);
  }

  // TODO
  // Add "item" to the front of the list
  // MUST be implemented with a one line call to "insert"
  void 
  push_front (const T& item)
  {
    insert(begin(), item);
  }

  // TODO
  // Remove the first element
  // MUST be implemented with a one line call to "erase"
  void
  pop_front  ()
  {
    erase(begin());
  }

  // TODO
  // Add "item" to the back of the list
  // MUST be implemented with a one line call to "insert"
  void 
  push_back  (const T& item)
  {
    insert(end(), item);
  }

  // TODO
  // Remove the last element
  // MUST be implemented with a one line call to "erase"
  void 
  pop_back   ()
  {
    erase(end());
  }

  // TODO
  // Return an iterator referencing the first element
  iterator       
  begin ()
  {
    return iterator(m_header.next);
  }

  // TODO
  const_iterator 
  begin () const
  {
    return const_iterator(m_header.next);
  }

  // TODO
  // Return an iterator referencing one past the last element,
  //   which will be the header node. 
  iterator       
  end   ()
  {
    return iterator(m_header.next+m_size);
  }

  // TODO
  const_iterator 
  end   () const
  {
    return const_iterator(m_header.next+m_size);
  }  

  // Insert "item" at position "i"
  // Return an iterator referencing the inserted element
  iterator 
  insert (iterator i, const T& item)
  {
  ListIterator<T> it = begin();
  while(it.m_nodePtr != i.m_nodePtr)
  {
    it.m_nodePtr = it.m_nodePtr->next;
  }
    Node* newNode = new Node(item, it.m_nodePtr->next, it.m_nodePtr->prev);
    it.m_nodePtr->next->prev = newNode;
    it.m_nodePtr->next = newNode;
    m_size++;
    return i;
  }

  // Erase element at position "i"
  // Return an iterator referencing the element beyond the one erased
  iterator 
  erase  (iterator i)
  {
  ListIterator<T> it = begin();
  while(it.m_nodePtr != i.m_nodePtr)
  {
    it.m_nodePtr = it.m_nodePtr->next;
  }
    return i;
  } 

  // Erase elements in the range [i1, i2)
  // Return iterator "i2"
  iterator 
  erase  (iterator i1, iterator i2)
  {
    return i2;
  }
  
private:
  // Dummy header
  Node   m_header;
  size_t m_size;
};

/************************************************************/

// Output operator
// Output "aList" in the format [ e1 e2 e3 ... en ]
//   E.g.: [ 3 8 21 ] for an integer list of size 3
// NOTE CAREFULLY the spacing and LACK of commas
// If you implement this incorrectly MOST of my tests will FAIL!
template <typename T>
ostream&
operator<< (ostream& out, const List<T>& aList)
{
  ListConstIterator<T> it = aList.begin();
  out << "[ ";
  for(size_t i = aList.size(); i > 0; i--)
  {
    out << it.m_nodePtr->data << " ";
    it.m_nodePtr = it.m_nodePtr->next;
  }
  out << ']';
  return out;
}

/************************************************************/

#endif

/************************************************************/