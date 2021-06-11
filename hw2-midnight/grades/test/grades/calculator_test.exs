defmodule Grades.CalculatorTest do
  use ExUnit.Case
  alias Grades.Calculator

  describe "percentage_grade/1" do
    test "percent_grade_tests" do
      assert 85 ==
               Calculator.percentage_grade(%{
                 homework: [0.8],
                 labs: [1, 1, 1],
                 midterm: 0.70,
                 final: 0.9
               })
	  assert 18 ==
               Calculator.percentage_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0.3,
                 final: 0.4
               })
    end
	
	test "letter_grade_tests" do
      assert "EIN" ==
               Calculator.letter_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0,
                 final: 0
               })
	  assert "A+" ==
               Calculator.letter_grade(%{
                 homework: [0.9],
                 labs: [1, 1, 1],
                 midterm: 0.8,
                 final: 0.9
               })
	  assert "A" ==
               Calculator.letter_grade(%{
                 homework: [0.8],
                 labs: [1, 1, 1],
                 midterm: 0.7,
                 final: 0.9
               })
	  assert "A-" ==
               Calculator.letter_grade(%{
                 homework: [0.8],
                 labs: [1, 0.9, 0.7],
                 midterm: 0.7,
                 final: 0.9
               })
      assert "B+" ==
               Calculator.letter_grade(%{
               homework: [0.7],
               labs: [0.9, 0.9, 0.9],
               midterm: 0.7,
               final: 0.8
             })
      assert "B" ==
             Calculator.letter_grade(%{
               homework: [0.8],
               labs: [0.7, 0.6, 0.7],
               midterm: 0.6,
               final: 0.7
             })
      assert "C+" ==
             Calculator.letter_grade(%{
               homework: [0.7],
               labs: [0.7, 0.6, 0.7],
               midterm: 0.6,
               final: 0.7
             })
      assert "C" ==
             Calculator.letter_grade(%{
               homework: [0.7],
               labs: [0.7, 0.5, 0.7],
               midterm: 0.6,
               final: 0.5
             })
      assert "D+" ==
             Calculator.letter_grade(%{
               homework: [0.5],
               labs: [0.7, 0.5, 0.7],
               midterm: 0.6,
               final: 0.5
             })
      assert "D" ==
             Calculator.letter_grade(%{
               homework: [0.5],
               labs: [0.7, 0.2, 0.7, 0.5],
               midterm: 0.6,
               final: 0.5
             })
      assert "E" ==
             Calculator.letter_grade(%{
               homework: [0.5],
               labs: [0.4, 0.4, 0.4],
               midterm: 0.5,
               final: 0.5
             })
      assert "F" ==
             Calculator.letter_grade(%{
               homework: [0.4],
               labs: [0.4, 0.4, 0.4],
               midterm: 0.5,
               final: 0.3
             })	   
    end
	
	test "numeric_grade_tests" do
      assert 0 ==
               Calculator.numeric_grade(%{
                 homework: [],
                 labs: [],
                 midterm: 0,
                 final: 0
               })
	  assert 10 ==
               Calculator.numeric_grade(%{
                 homework: [0.9],
                 labs: [1, 1, 1],
                 midterm: 0.8,
                 final: 0.9
               })
	  assert 9 ==
               Calculator.numeric_grade(%{
                 homework: [0.8],
                 labs: [1, 1, 1],
                 midterm: 0.7,
                 final: 0.9
               })
	  assert 8 ==
               Calculator.numeric_grade(%{
                 homework: [0.8],
                 labs: [1, 0.9, 0.7],
                 midterm: 0.7,
                 final: 0.9
               })
      assert 7 ==
               Calculator.numeric_grade(%{
               homework: [0.7],
               labs: [0.9, 0.9, 0.9],
               midterm: 0.7,
               final: 0.8
             })
      assert 6 ==
             Calculator.numeric_grade(%{
               homework: [0.8],
               labs: [0.7, 0.6, 0.7],
               midterm: 0.6,
               final: 0.7
             })
      assert 5 ==
             Calculator.numeric_grade(%{
               homework: [0.7],
               labs: [0.7, 0.6, 0.7],
               midterm: 0.6,
               final: 0.7
             })
      assert 4 ==
             Calculator.numeric_grade(%{
               homework: [0.7],
               labs: [0.7, 0.5, 0.7],
               midterm: 0.6,
               final: 0.5
             })
      assert 3 ==
             Calculator.numeric_grade(%{
               homework: [0.5],
               labs: [0.7, 0.5, 0.7],
               midterm: 0.6,
               final: 0.5
             })
      assert 2 ==
             Calculator.numeric_grade(%{
               homework: [0.5],
               labs: [0.7, 0.2, 0.7, 0.5],
               midterm: 0.6,
               final: 0.5
             })
      assert 1 ==
             Calculator.numeric_grade(%{
               homework: [0.5],
               labs: [0.4, 0.4, 0.4],
               midterm: 0.5,
               final: 0.5
             })
      assert 0 ==
             Calculator.numeric_grade(%{
               homework: [0.4],
               labs: [0.4, 0.4, 0.4],
               midterm: 0.5,
               final: 0.3
             })	   
    end
  end
end
