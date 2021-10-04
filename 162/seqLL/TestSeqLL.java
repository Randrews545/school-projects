import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSeqLL {

	@Test
	public void a_constructDouble( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>( );
		
		assertTrue("New seq is not empty", s.size( ) == 0);
		assertTrue("New seq has current", !s.isCurrent( ));
		assertTrue("Invariant fails #a1", s.verifyInvariant());
	}
	
	@Test
	public void b_constructString( ) {
		LinkedSeq<String> s = new LinkedSeq<String>( );
		
		assertTrue("New seq<String> is not empty", s.size( ) == 0);
		assertTrue("New seq has current", !s.isCurrent( ));
		assertTrue("Invariant fails #b1", s.verifyInvariant());
	}
	
	@Test(expected=IllegalStateException.class)
	public void c_noCurrentEmpty( ) {
		LinkedSeq<String> s = new LinkedSeq<String>( );
		s.getCurrent( );
		assertTrue("new seq should have no current to get - throw IllegalStateException", false);
		assertTrue("Invariant fails #c1", s.verifyInvariant());
	}
	
	@Test(expected=IllegalStateException.class)
	public void d_noAdvanceEmpty( ) {
		LinkedSeq<String> s = new LinkedSeq<String>( );
		s.advance( );
		assertTrue("new seq should not advance - throw IllegalStateException", false);
		assertTrue("Invariant fails #d1", s.verifyInvariant());
	}

	@Test
	public void e_cloneEmpty( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>( );
		LinkedSeq<Double> c = s.clone( );
		
		assertTrue("Cloned empty seq is not empty", c.size( ) == 0);
		assertTrue("Cloned empty seq has current", !c.isCurrent( ));
		assertTrue("Invariant fails #e1", s.verifyInvariant());
		assertTrue("Invariant fails #e2", c.verifyInvariant());
	}
	
	@Test
	public void f_addOneBefore( ) {
		String str = "spring";
		LinkedSeq<String> s = new LinkedSeq<String>( );
		s.addBefore(str);
		
		assertTrue("Should have one element", s.size( ) == 1);
		assertTrue("The element is current", s.isCurrent( ));
		assertTrue("Current should be " + str, str.equals(s.getCurrent( )));
		assertTrue("Invariant fails #f1", s.verifyInvariant());
		
		s.advance( );
		assertTrue("Should have no current", !s.isCurrent( ));		
		assertTrue("Invariant fails #f2", s.verifyInvariant());
	}
	
	@Test
	public void g_addOneAfter( ) {
		String str = "fall";
		LinkedSeq<String> s = new LinkedSeq<String>( );
		s.addAfter(str);
		
		assertTrue("Should have one element", s.size( ) == 1);
		assertTrue("The element is current", s.isCurrent( ));
		assertTrue("Current should be " + str, str.equals(s.getCurrent( )));
		assertTrue("Invariant fails #g1", s.verifyInvariant());
		
		s.advance( );
		assertTrue("Should have no current", !s.isCurrent( ));		
		assertTrue("Invariant fails #g2", s.verifyInvariant());
	}

	@Test
	public void h_mixtureLoop( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>( );
		
		assertTrue("Invariant fails #h1", s.verifyInvariant());
		s.addBefore((Double)23.0);
		assertTrue("Invariant fails #h2", s.verifyInvariant());
		s.addBefore((Double)14.0);
		assertTrue("Invariant fails #h3", s.verifyInvariant());
		s.addBefore((Double)(-15.2));
		assertTrue("Invariant fails #h4", s.verifyInvariant());
		s.addBefore((Double)17.0);
		assertTrue("Invariant fails #h5", s.verifyInvariant());
		
		assertEquals("Four addBefores", "(17.0) -15.2 14.0 23.0 ", s.toString( ));
		
		assertEquals("Size is four", 4, s.size( ));
		
		s.addAfter((Double)9.0);
		assertTrue("Invariant fails #h6", s.verifyInvariant());
		s.addAfter((Double)34.0);
		assertEquals("Two addAfters", "17.0 9.0 (34.0) -15.2 14.0 23.0 ", s.toString( ));
		assertTrue("Invariant fails #h7", s.verifyInvariant());
		
		s.advance( );
		assertEquals("Advance", "17.0 9.0 34.0 (-15.2) 14.0 23.0 ", s.toString( ));
		assertTrue("Invariant fails #h8", s.verifyInvariant());

		for(s.start( ); s.isCurrent( ); s.advance( )) {
		}
		assertTrue("Invariant fails #h9", s.verifyInvariant());
		
		assertTrue("Should be no current after loop", !s.isCurrent( ));
		
		s.start( );
		assertTrue("Should have current after start", s.isCurrent( ));
		assertEquals("Current value after start", 17, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #h10", s.verifyInvariant());
		
		s.advance( );
		assertEquals("Current value after advance", 9, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #h11", s.verifyInvariant());
	}

	@Test
	public void i_onlyAddBefore( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>();

		assertEquals("Size of new is not zero", 0, s.size( ));
		assertTrue("Should be no current without elements", !s.isCurrent( ));
		assertTrue("Invariant fails #i1", s.verifyInvariant());

		s.addBefore((Double)345.0);
		assertEquals("Size after add should be one", 1, s.size( ));
		assertEquals("Current value after adding 345", 345, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #i2", s.verifyInvariant());

		s.addBefore((Double)12.0);
		assertEquals("Size after add is two", 2, s.size( ));
		assertEquals("Current value after adding 12", 12, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #i3", s.verifyInvariant());

		s.addBefore((Double)789.0);
		assertEquals("Size after add is three", 3, s.size( ));
		assertEquals("Current value after adding 789", 789, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #i4", s.verifyInvariant());
	
		assertEquals("Three addBefores", "(789.0) 12.0 345.0 ", s.toString( ));
		assertTrue("Invariant fails #i5", s.verifyInvariant());
		
		s.advance( );
		assertTrue("Invariant fails #i6", s.verifyInvariant());
		s.advance( );
		assertTrue("Invariant fails #i7", s.verifyInvariant());
		
		assertEquals("Looking at last", "789.0 12.0 (345.0) ", s.toString( ));
		assertEquals("Current last value", 345, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #i8", s.verifyInvariant());

		s.advance( );
		assertTrue("No current after advancing past end", !s.isCurrent( ));
		assertTrue("Invariant fails #i9", s.verifyInvariant());
		
		assertEquals("At end with no current", "789.0 12.0 345.0 ", s.toString( ));

		s.addBefore((Double)17.35);
		assertTrue("Current at front", s.isCurrent( ));
		assertEquals("Current value after adding 17.35", 17.35, s.getCurrent( ), 0.0001);
		assertEquals("Size after add is four", 4, s.size( ));
		assertEquals("Added at front", "(17.35) 789.0 12.0 345.0 ", s.toString( ));		
		assertTrue("Invariant fails #i10", s.verifyInvariant());

		s.advance( ); // move to second element
		
		assertEquals("Looking at second", "17.35 (789.0) 12.0 345.0 ", s.toString( ));
		assertEquals("Current second value", 789, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #i11", s.verifyInvariant());
		
		s.addBefore((Double)3.14);
		assertEquals("Looking at new second", "17.35 (3.14) 789.0 12.0 345.0 ", s.toString( ));
		assertEquals("New second value", 3.14, s.getCurrent( ), 0.0001);		
		assertTrue("Invariant fails #i12", s.verifyInvariant());
	}


	@Test
	public void j_onlyAddAfter( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>( );

		assertEquals("Size of new is zero", 0, s.size( ));
		assertTrue("No current without elements", !s.isCurrent( ));
		assertTrue("Invariant fails #j1", s.verifyInvariant());

		s.addAfter((Double)13.6);
		assertEquals("Size after add is one", 1, s.size( ));
		assertEquals("Current value after adding 13.6", 13.6, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #j2", s.verifyInvariant());
		
		s.addAfter((Double)97.0);
		assertEquals("Size after add is two", 2, s.size( ));
		assertEquals("Current value after adding 97", 97, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #j3", s.verifyInvariant());

		s.addAfter((Double)(-225.0));
		assertEquals("Current value after adding -225", -225, s.getCurrent( ), 0.0001);
	
		assertEquals("Three addAfters", "13.6 97.0 (-225.0) ", s.toString( ));
		
		assertTrue("Current should exist", s.isCurrent( ));
		assertTrue("Invariant fails #j4", s.verifyInvariant());
		
		s.advance( );
		assertTrue("No current after advancing past end", !s.isCurrent( ));
		
		assertEquals("At end with no current", "13.6 97.0 -225.0 ", s.toString( ));
		assertTrue("Invariant fails #j5", s.verifyInvariant());

		s.addAfter((Double)17.35);
		assertTrue("Current should exist after add", s.isCurrent( ));
		assertEquals("Current value after adding 17.35", 17.35, s.getCurrent( ), 0.0001);
		assertEquals("Size after add is four", 4, s.size( ));
		assertEquals("At last item should be current", "13.6 97.0 -225.0 (17.35) ", s.toString( ));
		assertTrue("Invariant fails #j6", s.verifyInvariant());
		
		s.start( );
		assertTrue("Invariant fails #j7", s.verifyInvariant());
		s.addAfter((Double)3.14);
		assertEquals("Added second item", "13.6 (3.14) 97.0 -225.0 17.35 ", s.toString( ));
		assertEquals("Current value after adding 3.14", 3.14, s.getCurrent( ), 0.0001);		
		assertTrue("Invariant fails #j8", s.verifyInvariant());
	}

	@Test
	public void k_concat( ) {
		LinkedSeq<Double> A = new LinkedSeq<Double>( );
		LinkedSeq<Double> B = new LinkedSeq<Double>( );
		LinkedSeq<Double> C;

		C = LinkedSeq.concatenation(A, B);
		assertTrue("Should have no current after catenation", !C.isCurrent( ));
		assertTrue("Size is not 0", C.size( ) == 0);
		assertTrue("Invariant fails #k1", C.verifyInvariant());
		
		A.addAfter((Double)13.0);
		A.addAfter((Double)24.0);
		A.addAfter((Double)95.0);
		A.addAfter((Double)134.0);
		A.addAfter((Double)158.0);
		
		C = LinkedSeq.concatenation(A, B);
		assertTrue("Should have no current after catenation", !C.isCurrent( ));
		assertTrue("Size is added", C.size( ) == (A.size( ) + B.size( )));
		assertTrue("Invariant fails #k2", C.verifyInvariant());
		assertEquals("C from A, B does not have values from A", "13.0 24.0 95.0 134.0 158.0 ", C.toString( ));

		C = LinkedSeq.concatenation(B, A);
		assertTrue("Should have no current after catenation", !C.isCurrent( ));
		assertTrue("Size is added", C.size( ) == (A.size( ) + B.size( )));
		assertTrue("Invariant fails #k3", C.verifyInvariant());
		assertEquals("C from B, A does not have values from A", "13.0 24.0 95.0 134.0 158.0 ", C.toString( ));

		B.addBefore((Double)1024.0);
		B.addBefore((Double)987.0);
		B.addBefore((Double)919.0);
		B.addBefore((Double)798.0);
		B.addBefore((Double)733.45);
		B.addBefore((Double)717.0);
		B.addBefore((Double)700.0);
		B.addBefore((Double)652.0);
		B.addBefore((Double)512.0);
		B.addBefore((Double)498.0);
		B.addBefore((Double)436.0);
		B.addBefore((Double)303.0);
		B.addBefore((Double)300.001);
		B.addBefore((Double)256.0);
		assertEquals("Current value after adding 256", 256, B.getCurrent( ), 0.0001);
		
		C = LinkedSeq.concatenation(A, B);
		assertTrue("Should have no current after catenation", !C.isCurrent( ));
		System.out.println(A.size());
		System.out.println(B.size());
		System.out.println(C.size());
		assertTrue("Size is added", C.size( ) == (A.size( ) + B.size( )));

		assertEquals("At end with no current", "13.0 24.0 95.0 134.0 158.0 256.0 300.001 303.0 436.0 498.0 512.0 652.0 700.0 717.0 733.45 798.0 919.0 987.0 1024.0 ", C.toString( ));
		assertEquals("A unchanged", "13.0 24.0 95.0 134.0 (158.0) ", A.toString( ));
		assertEquals("B unchanged", "(256.0) 300.001 303.0 436.0 498.0 512.0 652.0 700.0 717.0 733.45 798.0 919.0 987.0 1024.0 ", B.toString( ));
		assertTrue("Invariant fails #k4", A.verifyInvariant());
		assertTrue("Invariant fails #k5", B.verifyInvariant());
		assertTrue("Invariant fails #k6", C.verifyInvariant());

		A.addAfter((Double)42.0);
		B.addBefore((Double)24.0);
		C.addAfter((Double)12.0);
		assertEquals("A added 42.0", "13.0 24.0 95.0 134.0 158.0 (42.0) ", A.toString( ));
		assertEquals("B added 24.0", "(24.0) 256.0 300.001 303.0 436.0 498.0 512.0 652.0 700.0 717.0 733.45 798.0 919.0 987.0 1024.0 ", B.toString( ));
		assertEquals("Added 12.0", "13.0 24.0 95.0 134.0 158.0 256.0 300.001 303.0 436.0 498.0 512.0 652.0 700.0 717.0 733.45 798.0 919.0 987.0 1024.0 (12.0) ", C.toString( ));
		assertTrue("Invariant fails #k7", A.verifyInvariant());
		assertTrue("Invariant fails #k8", B.verifyInvariant());
		assertTrue("Invariant fails #k9", C.verifyInvariant());
	}

	@Test
	public void l_noCurrent( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>( );
		
		try {
			s.advance( );
			assertTrue("Should have thrown exception in advance", false);
		} catch (IllegalStateException e) {
			
		}
		assertTrue("Invariant fails #l1", s.verifyInvariant());
		
		try {
			s.addBefore((Double)42.0);
			s.advance( );
			s.getCurrent( );
			assertTrue("Should have thrown exception in getCurrent", false);
		} catch (IllegalStateException e) {
			
		}
		assertTrue("Invariant fails #l2", s.verifyInvariant());
				
		try {
			s.removeCurrent( );
			assertTrue("Should have thrown exception in removeCurrent", false);
		} catch (IllegalStateException e) {
			
		}
		assertTrue("Invariant fails #l3", s.verifyInvariant());
	
	}

	@Test
	public void m_removeAddBefore( ) {
		LinkedSeq<String> s = new LinkedSeq<String>( );
		
		s.addBefore("car");
		s.addBefore("the");
		s.addBefore("drove");
		s.addBefore("he");
		assertEquals("he is current", "(he) drove the car ", s.toString( ));
		assertTrue("Invariant fails #m1", s.verifyInvariant());
		
		s.removeCurrent( );   // he  (first)
		assertEquals("drove is current", "(drove) the car ", s.toString( ));
		assertTrue("Invariant fails #m2", s.verifyInvariant());
		
		s.advance( );
		assertTrue("Invariant fails #m3", s.verifyInvariant());
		s.removeCurrent( );   // the  (middle)
		assertEquals("car is current", "drove (car) ", s.toString( ));
		assertTrue("Invariant fails #m4", s.verifyInvariant());
		
		s.removeCurrent( );   // car  (last)
		assertEquals("no current", "drove ", s.toString( ));
		assertTrue(!s.isCurrent( ));
		assertTrue("Invariant fails #m5", s.verifyInvariant());
		
		s.addBefore("She");
		assertEquals("Added at front", "(She) drove ", s.toString( ));		
		assertTrue("Invariant fails #m6", s.verifyInvariant());
	}

	@Test
	public void n_removeAddAfter( ) {
		LinkedSeq<String> s = new LinkedSeq<String>( );
		
		s.addAfter("the");
		s.addAfter("cow");
		s.addAfter("jumped");
		s.addAfter("high");
		assertEquals("high is current", "the cow jumped (high) ", s.toString( ));
		assertTrue("Invariant fails #n1", s.verifyInvariant());
		
		s.removeCurrent( );   // high  (last)
		assertEquals("no current", "the cow jumped ", s.toString( ));
		assertTrue(!s.isCurrent( ));
		assertTrue("Invariant fails #n2", s.verifyInvariant());
		
		s.start( );
		assertTrue("Invariant fails #n3", s.verifyInvariant());
		s.removeCurrent( );   // the  (first)
		assertEquals("cow current", "(cow) jumped ", s.toString( ));
		assertTrue("Invariant fails #n4", s.verifyInvariant());
		
		s.addAfter("gracefully");
		s.addAfter("and");
		s.addAfter("carefully");
		assertTrue("Invariant fails #n5", s.verifyInvariant());
		s.start( );
		assertTrue("Invariant fails #n6", s.verifyInvariant());
		s.advance( );
		assertEquals("gracefully current", "cow (gracefully) and carefully jumped ", s.toString( ));
		assertTrue("Invariant fails #n7", s.verifyInvariant());
		
		s.removeCurrent( );   // gracefully  (middle)
		assertTrue("Invariant fails #n8", s.verifyInvariant());
		s.removeCurrent( );   // and  (middle)
		
		assertEquals("carefully current", "cow (carefully) jumped ", s.toString( ));
		assertTrue("Invariant fails #n9", s.verifyInvariant());
	}
	
	@Test
	public void o_removeMix( ) {
		LinkedSeq<Double> s = new LinkedSeq<Double>( );

		s.addBefore((Double)42.0);
		assertEquals("Size is one after addBefore", 1, s.size( ));
		assertTrue("Item exists", s.isCurrent( ));
		assertTrue("Invariant fails #o1", s.verifyInvariant());
		s.removeCurrent( );
		assertEquals("Size is zero", 0, s.size( ));
		assertTrue("Item no longer exists", !s.isCurrent( ));
		assertTrue("Invariant fails #o2", s.verifyInvariant());
		
		s.addAfter((Double)17.0);
		assertEquals("Size is one after addAfter", 1, s.size( ));
		assertTrue("Item exists", s.isCurrent( ));
		assertTrue("Invariant fails #o3", s.verifyInvariant());
		s.removeCurrent( );
		assertEquals("Size is zero", 0, s.size( ));
		assertTrue("Item no longer exists", !s.isCurrent( ));
		assertTrue("Invariant fails #o4", s.verifyInvariant());
		
		s.addAfter((Double)23.0);
		s.addAfter((Double)79.56);
		s.addAfter((Double)893.23);
		s.addAfter((Double)1024.0);
		s.addAfter((Double)4096.0);
		s.addAfter((Double)24356.0);
		s.addAfter((Double)34251.0);
		s.addAfter((Double)42516.0);
		assertTrue("Invariant fails #o5", s.verifyInvariant());

		s.start( );
		assertEquals("Size is eight after several adds", 8, s.size( ));
		assertTrue("Invariant fails #o6", s.verifyInvariant());
		s.removeCurrent( );
		assertEquals("New current after remove", 79.56, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #o7", s.verifyInvariant());
		s.advance( );
		assertTrue("Invariant fails #o8", s.verifyInvariant());
		s.advance( );
		assertEquals("After advancing", 1024, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #o9", s.verifyInvariant());
		s.removeCurrent( );
		assertEquals("After removing in middle", 4096, s.getCurrent( ), 0.0001);
		assertTrue("Invariant fails #o10", s.verifyInvariant());
		s.advance( );
		assertTrue("Invariant fails #o11", s.verifyInvariant());
		s.advance( );
		assertTrue("Invariant fails #o12", s.verifyInvariant());
		s.advance( );
		assertTrue("Invariant fails #o13", s.verifyInvariant());
		s.removeCurrent( );
		assertTrue("After removing last", !s.isCurrent( ));
		assertEquals("Size is five after several removes", 5, s.size( ));
		assertTrue("Invariant fails #o14", s.verifyInvariant());
		
		s.start( );
		assertTrue("Invariant fails #o15", s.verifyInvariant());
		while (s.isCurrent( )) {
			s.removeCurrent( );
		}
		assertTrue("After removing all no current", !s.isCurrent( ));		
		assertEquals("Size is zero after removing all", 0, s.size( ));
		assertTrue("Invariant fails #o16", s.verifyInvariant());
		s.addAfter((Double)42.42);
		assertTrue("Add after removing all has current", s.isCurrent( ));		
		assertTrue("Invariant fails #o17", s.verifyInvariant());
	}

	@Test
	public void p_clonePlay( ) {
		LinkedSeq<Integer> A = new LinkedSeq<Integer>( );
		LinkedSeq<Integer> B = new LinkedSeq<Integer>( );
		LinkedSeq<Integer> C, D;	
		
		C = A.clone( );
		assertTrue("Clone of empty has empty size", C.size( ) == 0);
		assertTrue("Clone of empty has no current", !C.isCurrent( ));
		
		B.addBefore(17);
		D = B.clone( );
		assertTrue("Clone has same size", D.size( ) == 1);
		assertTrue("Clone has current", D.isCurrent( ));
		assertTrue("Clone has same current object value", 
				D.getCurrent( ).intValue( ) ==  B.getCurrent( ).intValue( ));
		assertTrue("Invariant fails #p1", A.verifyInvariant());
		assertTrue("Invariant fails #p2", B.verifyInvariant());
		assertTrue("Invariant fails #p3", C.verifyInvariant());
		assertTrue("Invariant fails #p4", D.verifyInvariant());
	}

	@Test
	public void q_cloneEquals( ) {
		LinkedSeq<Double> A = new LinkedSeq<Double>( );
		LinkedSeq<Double> B = new LinkedSeq<Double>( );
		LinkedSeq<Double> C;
		
		A.addAfter((Double)13.0);
		A.addAfter((Double)24.0);
		A.addAfter((Double)95.0);
		A.addAfter((Double)134.0);
		A.addAfter((Double)158.0);
		A.start( );

		B.addBefore((Double)158.0);
		B.addBefore((Double)134.0);
		B.addBefore((Double)95.0);
		B.addBefore((Double)24.0);
		B.addBefore((Double)13.0);
	
		assertTrue("A and B are not same object", !A.equals(B));
		assertTrue("B and A are not same object", !B.equals(A));
		assertTrue("A is equivalent to itself", A.equals(A));
		
		assertEquals("A's data", "(13.0) 24.0 95.0 134.0 158.0 ", A.toString( ));
		assertEquals("B's data", "(13.0) 24.0 95.0 134.0 158.0 ", B.toString( ));
		
		C = A.clone( );
		assertTrue("A and C are not same object", !A.equals(C));
		assertTrue("A and C are similar", A.isCurrent( ) == C.isCurrent());
		assertTrue("A and C are same size", A.size( ) == C.size( ));
		assertTrue("A and C have same current", A.getCurrent( ) == C.getCurrent( ));		
		assertEquals("C's data", "(13.0) 24.0 95.0 134.0 158.0 ", C.toString( ));
		assertTrue("Invariant fails #q1", A.verifyInvariant());
		assertTrue("Invariant fails #q2", B.verifyInvariant());
		assertTrue("Invariant fails #q3", C.verifyInvariant());
		
		A.addAfter((Double)17.25);
		assertEquals("C's data shouldn't change", "(13.0) 24.0 95.0 134.0 158.0 ", C.toString( ));
		assertEquals("A's data should change", "13.0 (17.25) 24.0 95.0 134.0 158.0 ", A.toString( ));
		assertTrue("Invariant fails #q4", A.verifyInvariant());
		assertTrue("Invariant fails #q5", B.verifyInvariant());
		assertTrue("Invariant fails #q6", C.verifyInvariant());
	}

	@Test
	public void r_charElements( ) {
		LinkedSeq<Character> s = new LinkedSeq<Character>( );
		assertTrue("Invariant fails #r1", s.verifyInvariant());
		
		s.addBefore((Character)'t');
		s.addBefore((Character)('a'));
		s.addBefore((Character)'p');
		
		assertEquals("Used addBefores 3 times", "(p) a t ", s.toString( ));
		
		assertEquals("Size is three", 3, s.size( ));
		assertTrue("Invariant fails #r2", s.verifyInvariant());
		
		s.addAfter((Character)'l');
		s.addAfter((Character)'e');
		assertEquals("Two addAfters", "p l (e) a t ", s.toString( ));
		assertTrue("Invariant fails #r3", s.verifyInvariant());
		
		s.advance( );
		assertEquals("Advance", "p l e (a) t ", s.toString( ));
		assertTrue("Invariant fails #r4", s.verifyInvariant());
	}

	@Test
	public void s_addAll( ) {
		LinkedSeq<Character> s = new LinkedSeq<Character>( );
		LinkedSeq<Character> t = new LinkedSeq<Character>( );
		LinkedSeq<Character> u = new LinkedSeq<Character>( );
		LinkedSeq<Character> v = new LinkedSeq<Character>( );
		
		s.addAfter((Character)'t');
		s.addAfter((Character)'i');
		s.addAfter((Character)'p');
		assertTrue("Invariant fails #s1", s.verifyInvariant());

		s.addAll(v);
		assertTrue("Invariant fails #s2", s.verifyInvariant());
		assertEquals("Inserted tip", "t i (p) ", s.toString( ));

		v.addAll(v);
		assertTrue("Invariant fails #s3", v.verifyInvariant());
		assertEquals("v should be empty", "", v.toString( ));

		v.addAll(s);
		assertTrue("Invariant fails #s4", v.verifyInvariant());
		assertEquals("v not tip", "t i p ", v.toString( ));

		t.addBefore((Character)'e');
		t.addBefore((Character)'o');
		t.addBefore((Character)'t');
		
		assertEquals("Insert tip", "t i (p) ", s.toString( ));
		
		assertEquals("Insert toe", "(t) o e ", t.toString( ));
		assertTrue("Invariant fails #s5", s.verifyInvariant());
		
		s.addAll(t);
		assertEquals("After addAll", "t i (p) t o e ", s.toString( ));
		assertTrue("Invariant fails #s6", s.verifyInvariant());
		assertTrue("Invariant fails #s7", t.verifyInvariant());
		
		s.advance( );
		s.advance( );
		s.advance( );
		s.addAfter((Character)'s');
		assertEquals("After adding s to end", "t i p t o e (s) ", s.toString( ));
		assertTrue("Invariant fails #s8", s.verifyInvariant());
		assertTrue("Invariant fails #s9", t.verifyInvariant());

		// t's last item should have been separate and not affected by
		// the addAfter in s above
		t.advance( );   
		t.advance( );
		assertEquals("t not affected by add in s", "t o (e) ", t.toString( ));

		assertEquals("u is empty", 0, u.size( ));				
		assertTrue("Invariant fails #s10", s.verifyInvariant());
		assertTrue("Invariant fails #s11", t.verifyInvariant());
		u.addAll(s);
		assertEquals("After adding to empty", "t i p t o e s ", u.toString( ));
		assertTrue("u should have no current item", !u.isCurrent( ));
		assertTrue("Invariant fails #s12", s.verifyInvariant());
		assertTrue("Invariant fails #s13", t.verifyInvariant());
		assertTrue("Invariant fails #s14", u.verifyInvariant());
		u.addAll(t);
		assertTrue("u should still have no current item", !u.isCurrent( ));
		assertEquals("a lot of items in u", "t i p t o e s t o e ", u.toString( ));		
		assertTrue("Invariant fails #s15", s.verifyInvariant());
		assertTrue("Invariant fails #s16", t.verifyInvariant());
		assertTrue("Invariant fails #s17", u.verifyInvariant());
	}
}
