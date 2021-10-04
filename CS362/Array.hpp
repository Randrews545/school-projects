/*
  Filename   : Array.hpp
  Author     : Gary M. Zoppetti
  Course     : CSCI 362-01
  Assignment : N/A
  Description: Array class, our implementation of a list ADT 
                 with random access and dynamic resizing.

		 With templated class we often include the
		 implementation of the class in the header
		 file. In these cases we use the extension
		 ".hpp". 
*/   

/************************************************************/
// Macro guard to prevent multiple inclusions
#ifndef ARRAY_H
#define ARRAY_H

/************************************************************/
// System includes

#include <iostream>
#include <cstdlib>
#include <algorithm>
#include <iterator>

/************************************************************/
// Local includes

/************************************************************/
// Using declarations

// YOU DO NOT NECESSARILY NEED TO USE ALL OF THESE!
using std::fill;
using std::copy;
using std::copy_backward;
using std::ostream;
using std::ptrdiff_t;
using std::distance;

/************************************************************/

template <typename T>
class Array 
{
public:
  //*****************************************************
  // DO NOT MODIFY THIS SECTION!
  // Some standard Container type aliases
  using value_type = T;
  // Iterators are just pointers to objects of type T
  using iterator = value_type*;
  using const_iterator = const value_type*;

  using reference = value_type&;
  using const_reference = const value_type&;

  using size_type = size_t;
  using difference_type = ptrdiff_t;
  //*****************************************************
  
  // Default ctor.
  // Initialize an empty Array.
  // This method is complete, and does NOT need modification. 
  // Remember to use a _member initialization list_ for each ctor,
  //   like I have below for the default ctor. 
  Array ()
    : m_size (0),
      m_capacity (0),
      m_array (nullptr)
  {
  }

  // Size ctor.
  // Initialize an Array of size "pSize", with each element
  //   set to "value". 
  explicit Array (size_t pSize, const T& value = T ())
    : m_size (pSize),
      m_capacity (pSize), 
      m_array (new T[m_capacity])
  {
    fill (begin (), end (), value);
  }

  // Range ctor.
  // Initialize an Array from the range [first, last).
  // "first" and "last" must be Array iterators or pointers
  //   into a primitive array. 
  Array (const_iterator first, const_iterator last)
  : m_size (distance(first, last)),
    m_capacity (m_size),
    m_array (new T[m_capacity])
  { 
    copy(first, last, m_array);
  }
  
  // Copy ctor.
  // Initialize this object from "a".
  Array (const Array& a) 
  : m_size (a.size()),
    m_capacity (a.size()),
    m_array (new T[m_capacity])
  {
    copy(a.begin(), a.end(), m_array);
  }
  
  // Destructor.
  // Release allocated memory.
  ~Array ()
  {
    delete[] m_array;
  }
  
  // Assignment operator.
  // Assign "a" to this object.
  //   Be careful to check for self-assignment.
  Array&
  operator= (const Array& a)
  {
    m_size = a.size();
    m_capacity = a.capacity();
    T* array = new T[m_size];
    copy (a.begin (), a.end (), array);
    delete[] m_array;
    m_array = array;
  }

  // Return the size.
  size_t
  size () const
  {
    return m_size;
  }
  
  // Return true if this Array is empty, false o/w.
  bool
  empty () const
  {
    if(m_size == 0)
    {
    return true;
    }
    else
    {
      return false;
    }
    
  }
  
  // Return the capacity.
  size_t
  capacity () const
  {
    return m_capacity;
  }

  // Return the element at position "index".
  T&
  operator[] (size_t index)
  {
    return m_array[index];
  }

  const T&
  operator[] (size_t index) const
  {
    return m_array[index];
  }

  // Insert an element at the back.
  void
  push_back (const T& item)
  {
    insert(end(), item);
  }

  // Erase the element at the back.
  void
  pop_back ()
  {
    erase(end());
  }

  // Reserve capacity for "space" elements.
  // "space" must be  greater than capacity. 
  //   If not, leave the capacity unchanged.
  // "size" must remain unchanged.
  void
  reserve (size_t space)
  {
    if (space > capacity ())
    {
      T* array = new T[space];
      copy (begin (), end (), array);
      delete[] m_array;
      m_array = array;
      m_capacity = space;
    }

    else if(space <= capacity())
    {

    }
  }

  // Change the size to be "newSize".
  // If "newSize" is less than "size",
  //   erase the last elements.
  // If "newSize" is more than "size", 
  //   insert "value"-s at the end.
  void
  resize (size_t newSize, const T& value = T ())
  {
    if(newSize < m_size)
    {
      T* array = new T[newSize];
      copy(begin(), begin() + newSize, array);
      delete [] m_array;
      m_array = array;
      m_size = newSize;
    }

    else if(newSize > m_size)
    {
      T* array = new T[newSize];
      copy(begin(), end(), array);
      delete [] m_array;
      m_array = array;
      fill(end()+1, end()+(newSize-distance(begin(),end())), value);
      m_size = newSize;
    }
  }

  // Insert "item" before "pos", and return iterator pointing to "item".
  // If the capacity is insufficient, DOUBLE it.
  //   If the capacity is 0, increase it to 1.
  // NOTE: If a reallocation occurs, "pos" will be invalidated!
  iterator
  insert (iterator pos, const T& item)
  {
    if(empty() == true)
    {
      size_t newpos = distance(begin(), pos);
      reserve(1);
      m_array[0] = item;
      ++m_size;
      pos = begin() + newpos;
      return pos;
    }
    else if(m_size == m_capacity && pos == begin())
    {
      size_t newpos = distance(begin(),pos);
      reserve(m_capacity*2);
      copy_backward(begin(), end(), end() + 1);
      m_array[newpos] = item;
      ++m_size;
      pos = begin();
      return pos;
    }
    else if(m_size == m_capacity && pos == end())
    {
      size_t newpos = distance(begin(),pos);
      reserve(m_capacity*2);
      m_array[newpos] = item;
      ++m_size;
      pos = begin() + newpos;
      return pos;
    }
    else if(m_size < m_capacity)
    {
      size_t newpos = distance(begin(), pos);
      copy_backward(pos, end(), end() + 1);
      m_array[newpos] = item;
      m_size++;
      pos = begin() + newpos;
      return pos;
    }
    return pos;
  }

  // Remove element at "pos", and return an iterator
  //   referencing the next element.
  iterator
  erase  (iterator pos)
  {
    if (pos == end())
    {
      size_t newpos = distance(begin(), pos - 1);
      T* array = new T[m_size - 1];
      copy(begin(), end() - 1, array);
      delete [] m_array;
      m_array = array;
      m_size--;
      pos = begin() + newpos;
      return pos;
    }
    else
    {
    size_t newpos = distance(begin(), pos);
    copy_backward(pos + 1, end(), end()-1);
    m_size--;
    pos = begin() + newpos;
    return pos;
    }
    return pos;
  }

  // Return iterator pointing to the first element.
  iterator
  begin ()
  {
    value_type *x;
    x = m_array;
    Array<T>::iterator begin = x;
    return begin;
  }

  const_iterator
  begin () const
  {
    value_type *x;
    x = m_array;
    Array<T>::iterator begin = x;
    return begin;
  }

  // Return iterator pointing one beyond the last element.
  iterator
  end ()
  {
    value_type *y;
    y = m_array;
    Array<T>::iterator end = y;
    end = end + m_size;
    return end;
  }

  const_iterator
  end () const
  {
    value_type *y;
    y = m_array;
    Array<T>::iterator end = y;
    end = end + m_size;
    return end;
  }

private:
  // Stores the number of elements in the Array. 
  size_t m_size;
  // Stores the capacity of the Array, which must be at least "m_size".
  size_t m_capacity;
  // Stores a pointer to the first element in the Array. 
  T*     m_array;
};

/************************************************************************/
// Free functions associated with the class

// Output operator. 
// Allows us to do "cout << a;", where "a" is an Array.
// DO NOT MODIFY!
template <typename T>
ostream&
operator<< (ostream& output, const Array<T>& a)
{
  output << "[ ";
  // This for-each loop will employ iterators. 
  for (const auto& elem : a)
    output << elem << " ";

  output << "]";

  return output;
}

#endif

/************************************************************************/