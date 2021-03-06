/**
 * Time : O(); Space : O()
 * @tag : Dynamic Programming; Backtracking; String
 * @by  : Steven Cooks
 * @date: Jul 2, 2015
 *******************************************************************************
 * Description: 
 * 
 * Implement regular expression matching with support for '.' and '*'. 
 * '.' Matches any single character. 
 * '*' Matches zero or more of the preceding element. 
 * The matching should cover the entire input string (not partial). 
 * 
 * The function prototype should be: 
 * 
 * bool isMatch(const char *s, const char *p) 
 * 
 * Some examples: 
 * 
 * isMatch("aa","a") → false 
 * isMatch("aa","aa") → true 
 * isMatch("aaa","aa") → false 
 * isMatch("aa", "a*") → true 
 * isMatch("aa", ".*") → true 
 * isMatch("ab", ".*") → true 
 * isMatch("aab", "c*a*b") → true
 * 
 *******************************************************************************
 * {@link https://leetcode.com/problems/regular-expression-matching/ }
 */
package _010_RegularExpressionMatching;

/** see test {@link _010_RegularExpressionMatching.SolutionDFSTest } */
public class SolutionDFS {

    public boolean isMatch(String s, String p) {
        int sIndex = 0;
        int pIndex = 0;
        return isMatchCore(sIndex, pIndex, s, p);
    }

    private boolean isMatchCore(int sIndex, int pIndex, String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        // base case
        if (sIndex == sLen && pIndex == pLen) {
            return true;
        }
        if (sIndex != sLen && pIndex == pLen) {
            return false;
        }
        // recursive case
        boolean matched = false;
        if ((pIndex + 1) < pLen && p.charAt(pIndex + 1) == '*') {
            if (sIndex < sLen && p.charAt(pIndex) == s.charAt(sIndex)
                    || (p.charAt(pIndex) == '.' && sIndex < sLen)) {
                // if equals or met with '.', then move on to the next state
                matched = isMatchCore(sIndex + 1, pIndex + 2, s, p)
                        || isMatchCore(sIndex + 1, pIndex, s, p)
                        || isMatchCore(sIndex, pIndex + 2, s, p);
            } else {
                // ignore a '*'
                matched = isMatchCore(sIndex, pIndex + 2, s, p);
            }
        } else if (sIndex < sLen && pIndex < pLen
                && s.charAt(sIndex) == p.charAt(pIndex)
                || (p.charAt(pIndex) == '.' && sIndex < sLen)) {
            matched = isMatchCore(sIndex + 1, pIndex + 1, s, p);
        }
        return matched;
    }

}
