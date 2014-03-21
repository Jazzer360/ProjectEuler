package drj.euler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * In the card game poker, a hand consists of five cards and are ranked, from
 * lowest to highest, in the following way:
 * 
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 *
 * If two players have the same ranked hands then the rank made up of the
 * highest value wins; for example, a pair of eights beats a pair of fives
 * (see example 1 below). But if two ranks tie, for example, both players have
 * a pair of queens, then highest cards in each hand are compared (see example
 * 4 below); if the highest cards tie then the next highest cards are compared,
 * and so on.
 *
 * Consider the following five hands dealt to two players:
 *
 * Hand		Player 1			Player 2				Winner
 * 1		5H 5C 6S 7S KD		2C 3S 8S 8D TD			Player 2
 * 			Pair of Fives		Pair of Eights
 * 
 * 2		5D 8C 9S JS AC		2C 5C 7D 8S QH			Player 1
 * 			Highest card Ace	Highest card Queen
 * 
 * 3		2D 9C AS AH AC		3D 6D 7D TD QD			Player 2
 * 			Three Aces			Flush with Diamonds
 * 
 * 4		4D 6S 9H QH QC		3D 6D 7H QD QS			Player 1
 * 			Pair of Queens		Pair of Queens
 * 			Highest card Nine	Highest card Seven
 * 
 * 5		2H 2D 4C 4D 4S		3C 3D 3S 9S 9D			Player 1
 * 			Full House			Full House
 * 			With Three Fours	with Three Threes
 * The file, poker.txt, contains one-thousand random hands dealt to two players.
 * Each line of the file contains ten cards (separated by a single space): the
 * first five are Player 1's cards and the last five are Player 2's cards. You
 * can assume that all hands are valid (no invalid characters or repeated
 * cards), each player's hand is in no specific order, and in each hand there
 * is a clear winner.
 * 
 * How many hands does Player 1 win?
 */
@Answer("376")
public class Problem054 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem054();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		String filePath = "external data/poker.txt";

		List<Hand> p1Hands = new ArrayList<Hand>();
		List<Hand> p2Hands = new ArrayList<Hand>();

		for (String line : Utility.getFileContents(filePath)) {
			p1Hands.add(new Hand(line.substring(0, 15).split(" ")));
			p2Hands.add(new Hand(line.substring(15).split(" ")));
		}

		int p1wins = 0;

		for (int hand = 0; hand < p1Hands.size(); hand++) {
			if (p1Hands.get(hand).beats(p2Hands.get(hand))) {
				p1wins++;
			}
		}

		return String.valueOf(p1wins);
	}

	public static class Hand {

		public static enum PokerHand {
			HIGH_CARD,
			PAIR {
				@Override
				boolean breakTie(Hand handOne, Hand handTwo) {
					Card.Rank h1pair = null;
					Card.Rank h2pair = null;
					Map<Card.Rank, Integer> h1map = handOne.getRankCounts();
					Map<Card.Rank, Integer> h2map = handTwo.getRankCounts();

					for (Card.Rank rank : h1map.keySet()) {
						if (h1map.get(rank) == 2) {
							h1pair = rank;
							break;
						}
					}

					for (Card.Rank rank : h2map.keySet()) {
						if (h2map.get(rank) == 2) {
							h2pair = rank;
							break;
						}
					}

					int compValue = h1pair.compareTo(h2pair);
					if (compValue == 0) {
						return super.breakTie(handOne, handTwo);
					}
					return h1pair.compareTo(h2pair) > 0 ? true : false;
				}
			},
			TWO_PAIR {
				@Override
				boolean breakTie(Hand handOne, Hand handTwo) {
					Card.Rank h1highPair = null;
					Card.Rank h1lowPair = null;
					Card.Rank h2highPair = null;
					Card.Rank h2lowPair = null;
					Map<Card.Rank, Integer> h1map = handOne.getRankCounts();
					Map<Card.Rank, Integer> h2map = handTwo.getRankCounts();

					for (Card.Rank rank : h1map.keySet()) {
						if (h1map.get(rank) == 2) {
							if (h1highPair == null) {
								h1highPair = rank;
							} else {
								if (rank.compareTo(h1highPair) > 0) {
									h1lowPair = h1highPair;
									h1highPair = rank;
								} else {
									h1lowPair = rank;
								}
								break;
							}
						}
					}

					for (Card.Rank rank : h2map.keySet()) {
						if (h2map.get(rank) == 2) {
							if (h2highPair == null) {
								h2highPair = rank;
							} else {
								if (rank.compareTo(h2highPair) > 0) {
									h2lowPair = h2highPair;
									h2highPair = rank;
								} else {
									h2lowPair = rank;
								}
								break;
							}
						}
					}

					if (h1highPair.compareTo(h2highPair) == 0) {
						if (h1lowPair.compareTo(h2lowPair) == 0) {
							return super.breakTie(handOne, handTwo);
						}
						return h1lowPair.compareTo(h2lowPair) > 0 ?
								true : false;
					}
					return h1highPair.compareTo(h2highPair) > 0 ? true : false;
				}
			},
			THREE_OF_A_KIND {
				@Override
				boolean breakTie(Hand handOne, Hand handTwo) {
					Card.Rank h1three = null;
					Card.Rank h2three = null;
					Map<Card.Rank, Integer> h1map = handOne.getRankCounts();
					Map<Card.Rank, Integer> h2map = handTwo.getRankCounts();

					for (Card.Rank rank : h1map.keySet()) {
						if (h1map.get(rank) == 3) {
							h1three = rank;
							break;
						}
					}

					for (Card.Rank rank : h2map.keySet()) {
						if (h2map.get(rank) == 3) {
							h2three = rank;
							break;
						}
					}

					return h1three.compareTo(h2three) > 0 ? true : false;
				}
			},
			STRAIGHT,
			FLUSH,
			FULL_HOUSE {
				@Override
				boolean breakTie(Hand handOne, Hand handTwo) {
					Card.Rank h1three = null;
					Card.Rank h2three = null;
					Map<Card.Rank, Integer> h1map = handOne.getRankCounts();
					Map<Card.Rank, Integer> h2map = handTwo.getRankCounts();

					for (Card.Rank rank : h1map.keySet()) {
						if (h1map.get(rank) == 3) {
							h1three = rank;
							break;
						}
					}

					for (Card.Rank rank : h2map.keySet()) {
						if (h2map.get(rank) == 3) {
							h2three = rank;
							break;
						}
					}

					return h1three.compareTo(h2three) > 0 ? true : false;
				}
			},
			FOUR_OF_A_KIND {
				@Override
				boolean breakTie(Hand handOne, Hand handTwo) {
					Card.Rank h1four = null;
					Card.Rank h2four = null;
					Map<Card.Rank, Integer> h1map = handOne.getRankCounts();
					Map<Card.Rank, Integer> h2map = handTwo.getRankCounts();

					for (Card.Rank rank : h1map.keySet()) {
						if (h1map.get(rank) == 4) {
							h1four = rank;
							break;
						}
					}

					for (Card.Rank rank : h2map.keySet()) {
						if (h2map.get(rank) == 4) {
							h2four = rank;
							break;
						}
					}

					return h1four.compareTo(h2four) > 0 ? true : false;
				}
			},
			STRAIGHT_FLUSH,
			ROYAL_FLUSH;

			boolean breakTie(Hand handOne, Hand handTwo) {
				List<Card> h1Cards = handOne.getCards();
				List<Card> h2Cards = handTwo.getCards();

				for (int i = 4; i >= 0; i--) {
					int compValue = h1Cards.get(i).getRank()
							.compareTo(h2Cards.get(i).getRank());
					if (compValue > 0) {
						return true;
					} else if (compValue < 0) {
						return false;
					}
				}

				throw new RuntimeException(
						"PokerHand breakTie() method cannot handle ties");
			}
		}

		private List<Card> cards = new ArrayList<>(5);
		private Map<Card.Rank, Integer> rankCounts = new HashMap<>();

		public Hand(String... cards) {
			for (String card : cards) {
				Card c = new Card(card);
				Card.Rank r = c.getRank();

				this.cards.add(c);

				int count = (rankCounts.containsKey(r) ? rankCounts.get(r) : 0);
				rankCounts.put(r, count + 1);
			}
			Collections.sort(this.cards);
		}

		public PokerHand getPokerHand() {
			if (rankCounts.containsValue(2)) {
				switch (rankCounts.size()) {
				case 4: return PokerHand.PAIR;
				case 3: return PokerHand.TWO_PAIR;
				case 2: return PokerHand.FULL_HOUSE;
				}
			} else if (rankCounts.containsValue(3)) {
				return PokerHand.THREE_OF_A_KIND;
			} else if (isStraight()) {
				if (isFlush()) {
					if (cards.get(4).getRank() == Card.Rank.ACE) {
						return PokerHand.ROYAL_FLUSH;
					}
					return PokerHand.STRAIGHT_FLUSH;
				}
				return PokerHand.STRAIGHT;
			} else if (isFlush()) {
				return PokerHand.FLUSH;
			}
			return PokerHand.HIGH_CARD;
		}

		public boolean beats(Hand hand) {
			PokerHand pokerHand = getPokerHand();
			int compValue = pokerHand.compareTo(hand.getPokerHand());
			if (compValue > 0) {
				return true;
			} else if (compValue < 0)
				return false;
			else {
				return pokerHand.breakTie(this, hand);
			}
		}

		@Override
		public String toString() {
			StringBuilder output = new StringBuilder();
			for (Card c : cards) {
				output.append(c.toString()).append(", ");
			}
			output.setLength(output.length() - 2);
			return output.toString();
		}

		private List<Card> getCards() {
			return cards;
		}

		private Map<Card.Rank, Integer> getRankCounts() {
			return rankCounts;
		}

		private boolean isStraight() {
			for (int i = 0; i < 4; i++) {
				Card c1 = cards.get(i);
				Card c2 = cards.get(i + 1);
				if (c2.getRank().getValue() != c1.getRank().getValue() + 1) {
					if (i == 3 && cards.get(0).getRank() == Card.Rank.TWO &&
							cards.get(4).getRank() == Card.Rank.ACE) {
						return true;
					}
					return false;
				}
			}
			return true;
		}

		private boolean isFlush() {
			Card.Suit suit = cards.get(4).getSuit();
			for (Card card : cards) {
				if (card.getSuit() != suit) {
					return false;
				}
			}
			return true;
		}
	}

	public static class Card implements Comparable<Card> {

		public static enum Rank {
			TWO (2), THREE (3), FOUR (4), FIVE (5), SIX (6), SEVEN (7),
			EIGHT (8), NINE (9), TEN (10), JACK (11), QUEEN (12), KING (13),
			ACE (14);

			private int value;

			Rank(int value) {
				this.value = value;
			}

			int getValue() {
				return value;
			}

			static Rank getRank(char character) {
				switch (character) {
				case '2': return TWO;
				case '3': return THREE;
				case '4': return FOUR;
				case '5': return FIVE;
				case '6': return SIX;
				case '7': return SEVEN;
				case '8': return EIGHT;
				case '9': return NINE;
				case 'T': return TEN;
				case 'J': return JACK;
				case 'Q': return QUEEN;
				case 'K': return KING;
				case 'A': return ACE;
				default: return null;
				}
			}
		}

		public static enum Suit {
			CLUBS, DIAMONDS, HEARTS, SPADES;

			private static Suit getSuit(char character) {
				switch (character) {
				case 'C': return CLUBS;
				case 'D': return DIAMONDS;
				case 'H': return HEARTS;
				case 'S': return SPADES;
				default: return null;
				}
			}
		}

		private Rank rank;
		private Suit suit;

		public Card (String card) {
			rank = Rank.getRank(card.charAt(0));
			suit = Suit.getSuit(card.charAt(1));
		}

		public Rank getRank() {
			return rank;
		}

		public Suit getSuit() {
			return suit;
		}

		@Override
		public int compareTo(Card o) {
			return rank.compareTo(o.rank);
		}

		@Override
		public String toString() {
			return rank.toString() + " " + suit.toString();
		}
	}
}
