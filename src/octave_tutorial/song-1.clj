(ns cmj.overtone
  (:use [overtone.live]
        [overtone.inst.piano]
        [overtone.inst.synth]
        [overtone.inst.drum]))

;(kick)
;(noise-snare)
;(open-hat)
;(grunge-bass 50 0.5 1 0.01 0.4 1)
;(tb303 35 1 0.8 0.01 0.1 0.6 0.25 100 0.1 0.5)
;(overpad)
;(piano)
;(stop)
;(kick2)

;; converts a note (i.e. :C4) to a MIDI value
(defn note->hz [music-note]
    (midi->hz (note music-note)))

;; plays a tb303 bass note
(defn tb-bass [input-note]
  (tb303 (note input-note) 1 0.8 0.01 0.1 0.6 0.25 100 0.1 0.99))

;; plays a note on the given instrument
(defn play-note [music-note instr]
    (instr (midi->hz (note music-note))))

;; plays a note on the piano
(defn piano-play [music-note]
  (piano (note music-note)))

;; plays a note on the overpad
(defn overpad-play [note-input]
  (overpad (note note-input)))

;; plays a chord on SAW2
(defn play-chord [a-chord]
  (doseq [note a-chord] (saw2 note)))

;; plays a chord on the given instrument
(defn play-chord-inst [a-chord inst]
  (doseq [note a-chord] (inst note)))


;; the drum, bass, and chord progression for the verse
(defn verse-loop [m beat-num]

  (at (m (+ 0 beat-num)) (kick))
  (at (m (+ 1 beat-num)) (noise-snare))
  (at (m (+ 1 beat-num)) (play-chord (chord :F3 :major7)))
  (at (m (+ 1 beat-num)) (tb-bass :F2))
  (at (m (+ 2.5 beat-num)) (open-hat))
  (at (m (+ 2.5 beat-num)) (kick))
  (at (m (+ 3.5 beat-num)) (kick))
  (at (m (+ 3 beat-num)) (tb-bass :A2))
  (at (m (+ 4 beat-num)) (kick))
  (at (m (+ 5 beat-num)) (play-chord (chord :C3 :major7)))
  (at (m (+ 5 beat-num)) (tb-bass :C3))
  (at (m (+ 5 beat-num)) (noise-snare))
  (at (m (+ 6.5 beat-num)) (open-hat))
  (at (m (+ 7.5 beat-num)) (kick))
  (at (m (+ 7 beat-num)) (tb-bass :B3))
  (at (m (+ 7.5 beat-num)) (tb-bass :G3))
  (at (m (+ 8 beat-num)) (tb-bass :E2))
  )

;; the BPM for the metronome
(def metro (metronome 145))

;; plays the verse lead line on the given instrument
(defn lead-line [m beat-num instr-play]

  (at (m (+ 2 beat-num)) (instr-play :G4))
  (at (m (+ 2.5 beat-num)) (instr-play :A4))
  (at (m (+ 3 beat-num)) (instr-play :B4))
  (at (m (+ 6 beat-num)) (instr-play :G4))
  (at (m (+ 6.5 beat-num)) (instr-play :A4))
  (at (m (+ 7 beat-num)) (instr-play :D5))
  (at (m (+ 7.5 beat-num)) (instr-play :B4))
)

;; plays the verse alternate lead line on the given instrument
(defn lead-line-alt [m beat-num instr-play]

  (at (m (+ 2 beat-num)) (instr-play :G4))
  (at (m (+ 2.5 beat-num)) (instr-play :A4))
  (at (m (+ 3 beat-num)) (instr-play :B4))
  (at (m (+ 6 beat-num)) (instr-play :G4))
  (at (m (+ 6.5 beat-num)) (instr-play :A4))
  (at (m (+ 7 beat-num)) (instr-play :f4))
  (at (m (+ 7 beat-num)) (instr-play :d4))
  (at (m (+ 7.5 beat-num)) (instr-play :e4))
  (at (m (+ 7.5 beat-num)) (instr-play :c4)))

;; the drum, bass, and chord progression for the first hook
(defn hook [m beat-num]
  (at (m (+ 0 beat-num)) (kick))
  (at (m (+ 1 beat-num)) (noise-snare))
  (at (m (+ 1 beat-num)) (play-chord-inst (chord :bb2 :maj9) overpad))
  (at (m (+ 1 beat-num)) (tb-bass :Bb2))
  (at (m (+ 2.5 beat-num)) (open-hat))
  (at (m (+ 2.5 beat-num)) (kick))
  (at (m (+ 3.5 beat-num)) (kick))
  (at (m (+ 3 beat-num)) (tb-bass :F3))
  (at (m (+ 4 beat-num)) (kick))
  (at (m (+ 4 beat-num)) (kill buzz))
  (at (m (+ 5 beat-num)) (play-chord-inst (chord :d2 :m7+9) overpad))
  (at (m (+ 5 beat-num)) (tb-bass :A3))
  (at (m (+ 5 beat-num)) (noise-snare))
  (at (m (+ 6.5 beat-num)) (open-hat))
  (at (m (+ 7.5 beat-num)) (kick))
  (at (m (+ 7 beat-num)) (tb-bass :C3))
  (at (m (+ 7.5 beat-num)) (tb-bass :D2))
  (at (m (+ 8 beat-num)) (tb-bass :C2))
  (at (m (+ 8 beat-num)) (kill buzz))
  )
;; the drum, bass, and chord progression for the second hook
(defn hook2 [m beat-num]
  (at (m (+ 0 beat-num)) (kick))
  (at (m (+ 1 beat-num)) (play-chord-inst (chord :F2 :major7) overpad))
  (at (m (+ 1 beat-num)) (tb-bass :f2))
  (at (m (+ 3 beat-num)) (tb-bass :a2))
  (at (m (+ 4 beat-num)) (kick))
  (at (m (+ 4 beat-num)) (kill buzz))
  (at (m (+ 5 beat-num)) (play-chord-inst (chord :f3 :maj9) overpad))
  (at (m (+ 5 beat-num)) (tb-bass :g3))
  (at (m (+ 7.5 beat-num)) (tb-bass :D2))
  (at (m (+ 8 beat-num)) (tb-bass :C2))
  (at (m (+ 8 beat-num)) (kill buzz)))

;; plays the lead line for the hook on the given instrument
(defn hook-lead [m beat-num instr-play]
  (at (m (+ 2 beat-num)) (instr-play :a4))
  (at (m (+ 2.5 beat-num)) (instr-play :g4))
  (at (m (+ 3 beat-num)) (instr-play :a4))
  (at (m (+ 6 beat-num)) (instr-play :e4))
  (at (m (+ 6.5 beat-num)) (instr-play :a4))
  (at (m (+ 7 beat-num)) (instr-play :g5))
  (at (m (+ 7.5 beat-num)) (instr-play :a4))
  )

;; the set of all notes to be used in the solos
(def note-selection [:c4
                     :d4
                     :e4
                     :F4
                     :G4
                     :A4
                     :B4
                     :c5
                     :D5
                     :e5
                     :f5
                     :g5
                     :a5
                     :b5
                     :c6
                     :d6
                     :e6
                     :f6
                     :g6])

;; chooses a random note from a set of notes
(defn choose-rand [notes]
  (nth notes (rand-int (count notes))))

;; plays the solo on the given instrument with the given tempo
(defn solo [m beat-num notes instr-play]
  (at (m (+ 1 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 2 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 3 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 3.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 4 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 4.25 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 4.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6.33 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6.66 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 7 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 7.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 8 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 9 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 11 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 13 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 13.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 14 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15.33 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15.75 beat-num)) (instr-play (choose-rand notes))))

;; plays this alternate solo on the given insutrment with the given tempo
(defn solo2 [m beat-num notes instr-play]
  (at (m (+ 1 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 1.33 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 1.66 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 2.0 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 2.66 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 2.95 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 3.0 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6.2 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6.4 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6.6 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 6.8 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 7.0 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 8 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 9.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 11 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 13 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 13.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 14 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15.33 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15.75 beat-num)) (instr-play (choose-rand notes))))

;; plays this alternate solo on the given instrument with the given tempo
(defn solo3 [m beat-num notes instr-play]
  (at (m (+ 1 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 3 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 4 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 7 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 8 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 8.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 9 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 11 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 12 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 12.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 13.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 14 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 14.5 beat-num)) (instr-play (choose-rand notes)))
  (at (m (+ 15 beat-num)) (instr-play :c4)))

;; plays the song
(defn execute-song []
  (do

    (verse-loop metro (metro))

    (verse-loop metro (+ 8 (metro)))

    (verse-loop metro (+ 16 (metro)))
    (lead-line metro (+ 16 (metro)) piano-play)

    (verse-loop metro (+ 24 (metro)))
    (lead-line metro (+ 24 (metro)) piano-play)

    (verse-loop metro (+ 32 (metro)))
    (lead-line metro (+ 32 (metro)) piano-play)

    (verse-loop metro (+ 40 (metro)))
    (lead-line-alt metro (+ 40 (metro)) piano-play)

    (hook metro (+ 48 (metro)))
    (hook metro (+ 56 (metro)))

    (hook2 metro (+ 64 (metro)))
    (hook2 metro (+ 72 (metro)))

    (hook metro (+ 80 (metro)))
    (solo3 metro (+ 80 (metro)) note-selection overpad-play)
    (hook metro (+ 88 (metro)))

    (hook2 metro (+ 96 (metro)))
    (hook2 metro (+ 104 (metro)))
    (solo3 metro (+ 96 (metro)) note-selection piano-play)
    ))

(execute-song)
