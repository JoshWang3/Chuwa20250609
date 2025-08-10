/**
 * JavaScript Homework - 5 LeetCode Problems Solutions
 */

// Problem 1: Two Sum (Easy)
// Given an array of integers nums and an integer target, return indices of two numbers that add up to target.

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
function twoSum(nums, target) {
    const map = new Map();
    
    for (let i = 0; i < nums.length; i++) {
        const complement = target - nums[i];
        
        if (map.has(complement)) {
            return [map.get(complement), i];
        }
        
        map.set(nums[i], i);
    }
    
    return [];
}

// Test case
console.log("Problem 1 - Two Sum:");
console.log(twoSum([2, 7, 11, 15], 9)); // [0, 1]
console.log(twoSum([3, 2, 4], 6)); // [1, 2]

// Problem 2: Reverse Integer (Medium)
// Given a signed 32-bit integer x, return x with its digits reversed.

/**
 * @param {number} x
 * @return {number}
 */
function reverse(x) {
    const isNegative = x < 0;
    const reversed = parseInt(Math.abs(x).toString().split('').reverse().join(''));
    
    // Check for 32-bit integer overflow
    if (reversed > Math.pow(2, 31) - 1) {
        return 0;
    }
    
    return isNegative ? -reversed : reversed;
}

// Test case
console.log("\nProblem 2 - Reverse Integer:");
console.log(reverse(123)); // 321
console.log(reverse(-123)); // -321
console.log(reverse(120)); // 21

// Problem 3: Palindrome Number (Easy)
// Given an integer x, return true if x is a palindrome, and false otherwise.

/**
 * @param {number} x
 * @return {boolean}
 */
function isPalindrome(x) {
    if (x < 0) return false;
    
    const str = x.toString();
    let left = 0;
    let right = str.length - 1;
    
    while (left < right) {
        if (str[left] !== str[right]) {
            return false;
        }
        left++;
        right--;
    }
    
    return true;
}

// Test case
console.log("\nProblem 3 - Palindrome Number:");
console.log(isPalindrome(121)); // true
console.log(isPalindrome(-121)); // false
console.log(isPalindrome(10)); // false

// Problem 4: Valid Parentheses (Easy)
// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

/**
 * @param {string} s
 * @return {boolean}
 */
function isValid(s) {
    const stack = [];
    const pairs = {
        ')': '(',
        '}': '{',
        ']': '['
    };
    
    for (let char of s) {
        if (char === '(' || char === '{' || char === '[') {
            stack.push(char);
        } else if (char === ')' || char === '}' || char === ']') {
            if (stack.length === 0 || stack.pop() !== pairs[char]) {
                return false;
            }
        }
    }
    
    return stack.length === 0;
}

// Test case
console.log("\nProblem 4 - Valid Parentheses:");
console.log(isValid("()")); // true
console.log(isValid("()[]{}")); // true
console.log(isValid("(]")); // false

// Problem 5: Merge Two Sorted Lists (Easy)
// You are given the heads of two sorted linked lists list1 and list2. Merge the two lists into one sorted list.

// Definition for singly-linked list
function ListNode(val, next) {
    this.val = (val === undefined ? 0 : val);
    this.next = (next === undefined ? null : next);
}

/**
 * @param {ListNode} list1
 * @param {ListNode} list2
 * @return {ListNode}
 */
function mergeTwoLists(list1, list2) {
    const dummy = new ListNode(0);
    let current = dummy;
    
    while (list1 !== null && list2 !== null) {
        if (list1.val <= list2.val) {
            current.next = list1;
            list1 = list1.next;
        } else {
            current.next = list2;
            list2 = list2.next;
        }
        current = current.next;
    }
    
    // Append remaining nodes
    current.next = list1 || list2;
    
    return dummy.next;
}

// Helper function to create linked list from array
function createLinkedList(arr) {
    if (arr.length === 0) return null;
    
    const head = new ListNode(arr[0]);
    let current = head;
    
    for (let i = 1; i < arr.length; i++) {
        current.next = new ListNode(arr[i]);
        current = current.next;
    }
    
    return head;
}

// Helper function to convert linked list to array for testing
function linkedListToArray(head) {
    const result = [];
    let current = head;
    
    while (current !== null) {
        result.push(current.val);
        current = current.next;
    }
    
    return result;
}

// Test case
console.log("\nProblem 5 - Merge Two Sorted Lists:");
const list1 = createLinkedList([1, 2, 4]);
const list2 = createLinkedList([1, 3, 4]);
const merged = mergeTwoLists(list1, list2);
console.log(linkedListToArray(merged)); // [1, 1, 2, 3, 4, 4]

console.log("\n=== All LeetCode Problems Solved! ===");