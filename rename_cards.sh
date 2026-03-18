#!/bin/bash
# Usage: bash rename_cards.sh "path/to/folder"
# Renames short card names (AS, KH, 10D, etc.) to full names (ace_of_spades.jpg, etc.)

FOLDER="$1"

if [ -z "$FOLDER" ]; then
    echo "Usage: bash rename_cards.sh \"path/to/folder\""
    exit 1
fi

declare -A MAP=(
    # Spades
    ["AS"]="ace_of_spades"
    ["2S"]="2_of_spades"
    ["3S"]="3_of_spades"
    ["4S"]="4_of_spades"
    ["5S"]="5_of_spades"
    ["6S"]="6_of_spades"
    ["7S"]="7_of_spades"
    ["8S"]="8_of_spades"
    ["9S"]="9_of_spades"
    ["10S"]="10_of_spades"
    ["JS"]="jack_of_spades"
    ["QS"]="queen_of_spades"
    ["KS"]="king_of_spades"
    # Hearts
    ["AH"]="ace_of_hearts"
    ["2H"]="2_of_hearts"
    ["3H"]="3_of_hearts"
    ["4H"]="4_of_hearts"
    ["5H"]="5_of_hearts"
    ["6H"]="6_of_hearts"
    ["7H"]="7_of_hearts"
    ["8H"]="8_of_hearts"
    ["9H"]="9_of_hearts"
    ["10H"]="10_of_hearts"
    ["JH"]="jack_of_hearts"
    ["QH"]="queen_of_hearts"
    ["KH"]="king_of_hearts"
    # Diamonds
    ["AD"]="ace_of_diamonds"
    ["2D"]="2_of_diamonds"
    ["3D"]="3_of_diamonds"
    ["4D"]="4_of_diamonds"
    ["5D"]="5_of_diamonds"
    ["6D"]="6_of_diamonds"
    ["7D"]="7_of_diamonds"
    ["8D"]="8_of_diamonds"
    ["9D"]="9_of_diamonds"
    ["10D"]="10_of_diamonds"
    ["JD"]="jack_of_diamonds"
    ["QD"]="queen_of_diamonds"
    ["KD"]="king_of_diamonds"
    # Clubs
    ["AC"]="ace_of_clubs"
    ["2C"]="2_of_clubs"
    ["3C"]="3_of_clubs"
    ["4C"]="4_of_clubs"
    ["5C"]="5_of_clubs"
    ["6C"]="6_of_clubs"
    ["7C"]="7_of_clubs"
    ["8C"]="8_of_clubs"
    ["9C"]="9_of_clubs"
    ["10C"]="10_of_clubs"
    ["JC"]="jack_of_clubs"
    ["QC"]="queen_of_clubs"
    ["KC"]="king_of_clubs"
    # Extras
    ["CB"]="card_back"
    ["CB2"]="card_back_2"
    ["CB3"]="card_back_3"
    ["JB"]="joker_blue"
    ["JR"]="joker_red"
    ["JG"]="joker_gold"
    ["JK"]="joker_black"
)

count=0
skipped=0

for file in "$FOLDER"/*.jpg "$FOLDER"/*.png; do
    [ -f "$file" ] || continue
    basename="${file%.*}"
    ext="${file##*.}"
    shortname="${basename##*/}"

    if [ -n "${MAP[$shortname]}" ]; then
        newname="${MAP[$shortname]}.$ext"
        mv "$file" "$FOLDER/$newname"
        echo "Renamed: $shortname.$ext -> $newname"
        ((count++))
    else
        echo "SKIPPED (unknown): $shortname.$ext"
        ((skipped++))
    fi
done

echo ""
echo "Done. Renamed: $count  |  Skipped: $skipped"
